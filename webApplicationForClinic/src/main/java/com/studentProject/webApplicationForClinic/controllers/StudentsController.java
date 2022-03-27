package com.studentProject.webApplicationForClinic.controllers;


import com.studentProject.webApplicationForClinic.classes.ExcelExporter;
import com.studentProject.webApplicationForClinic.classes.WordReader;
import com.studentProject.webApplicationForClinic.dao.StudentsDAO;
import com.studentProject.webApplicationForClinic.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final StudentsDAO studentsDAO;

    @Autowired
    public StudentsController(StudentsDAO studentsDAO) {
        this.studentsDAO = studentsDAO;
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
                for (Student oneStudent : students) {
                    studentsDAO.save(oneStudent);
                }
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
    public String index(Model model) {
        model.addAttribute("students", studentsDAO.index());
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
        List<Student> studentList = studentsDAO.index();

        ExcelExporter excelExporter = new ExcelExporter(studentList);

        excelExporter.export(response);
    }

}
