package com.studentProject.webApplicationForClinic.dao;

import com.studentProject.webApplicationForClinic.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentsDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentsDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void save(Student newStudent) {
        jdbcTemplate.update("INSERT INTO students VALUES(DEFAULT,?,?,?,?,?)",newStudent.getName(),newStudent.getSurname(),newStudent.getDateOfBirth(),newStudent.getFaculty(),newStudent.getReferences());
    }
    public List<Student> index() {
        return jdbcTemplate.query("SELECT * FROM students",new BeanPropertyRowMapper<>(Student.class));
    }

}
