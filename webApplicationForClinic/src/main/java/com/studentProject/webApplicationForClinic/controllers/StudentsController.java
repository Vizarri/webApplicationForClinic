package com.studentProject.webApplicationForClinic.controllers;


import com.studentProject.webApplicationForClinic.classes.ExcelExporter;
import com.studentProject.webApplicationForClinic.classes.WordReader;
import com.studentProject.webApplicationForClinic.models.Student;
import com.studentProject.webApplicationForClinic.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @PostMapping("/download")
    @PreAuthorize("hasAuthority('permission:write')")
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Залетели в ПОСТ МЕТОД");
        System.out.println(file.toString());
        modelMap.addAttribute("file", file);
        if (!file.isEmpty()) {
            try {
                List<Student> students = WordReader.readWordFile(file);
                studentRepository.saveAll(students);
                return "redirect:/students/allStudents";
            } catch (Exception e) {
                System.out.println("Вам не удалось загрузить ");
                System.out.println("Ошибка " + e);
                return "students/import/error";
            }
        } else {
            System.out.println("Вам не удалось загрузить  потому что файл пустой.");
            return "students/import/error";
        }
    }

    @GetMapping("/allStudents")
    @PreAuthorize("hasAuthority('permission:read')")
    public String index(Model model, @ModelAttribute("student") Student student) {
        model.addAttribute("students", studentRepository.findAll());
        return "students/allStudents";
    }

    @GetMapping("/export/excel")
    @PreAuthorize("hasAuthority('permission:read')")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Student> studentList = studentRepository.findAll();

        ExcelExporter excelExporter = new ExcelExporter(studentList);

        excelExporter.export(response);
    }

    @PostMapping("/newStudent")
    @PreAuthorize("hasAuthority('permission:write')")
    public String createNewStudent(Student student) {
        System.out.println("Пришедшая модель " + student.toString());
        studentRepository.save(student);
        return "redirect:/students/allStudents";

    }


}
