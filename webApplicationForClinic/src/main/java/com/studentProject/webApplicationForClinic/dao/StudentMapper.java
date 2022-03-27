package com.studentProject.webApplicationForClinic.dao;



import com.studentProject.webApplicationForClinic.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));
        student.setDateOfBirth(resultSet.getString("dateOfBirth"));
        student.setFaculty(resultSet.getString("faculty"));
        student.setReferences(resultSet.getString("references"));

        return student;
    }
}
