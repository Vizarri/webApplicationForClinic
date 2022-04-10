package com.studentProject.webApplicationForClinic.repository;


import com.studentProject.webApplicationForClinic.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

