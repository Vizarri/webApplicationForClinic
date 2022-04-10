package com.studentProject.webApplicationForClinic.classes;

import com.studentProject.webApplicationForClinic.models.Student;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordReader {

    private MultipartFile file;

    public WordReader(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public static List<Student> readWordFile(MultipartFile stream) throws IOException {
        List<Student> students = new ArrayList<>();
        XWPFDocument doc = new XWPFDocument(stream.getInputStream());

        Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();
        //Iterate through the list and check for table element type
        while (docElementsIterator.hasNext()) {
            IBodyElement docElement = docElementsIterator.next();
            if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                //Get List of table and iterate it
                List<XWPFTable> xwpfTableList = docElement.getBody().getTables();
                for (XWPFTable xwpfTable : xwpfTableList) {
                    System.out.println("Total Rows : " + xwpfTable.getNumberOfRows());
                    // начинаем с 1 так как в 0 идет шапка
                    for (int i = 1; i < xwpfTable.getRows().size(); i++) {
                        System.out.println("НОВАЯ СТРОКА");
/*                        if (i == 0) {
                            continue;
                        }*/
                        Student student = new Student();
/*                        for (int j = 0; j < xwpfTable.getRow(i).getTableCells().size(); j++) {
                            System.out.println(xwpfTable.getRow(i).getCell(j).getText());
                        }*/
                        student.setFio(xwpfTable.getRow(i).getCell(0).getText());
                        student.setGender(xwpfTable.getRow(i).getCell(1).getText());
                        student.setDateOfBirth(xwpfTable.getRow(i).getCell(2).getText());
                        student.setFaculty(xwpfTable.getRow(i).getCell(3).getText());
                        student.setReference(xwpfTable.getRow(i).getCell(4).getText());
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }
}

