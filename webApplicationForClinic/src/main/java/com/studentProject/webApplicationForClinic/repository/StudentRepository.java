package com.studentProject.webApplicationForClinic.repository;


import com.studentProject.webApplicationForClinic.models.Student;
import com.studentProject.webApplicationForClinic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);
}

