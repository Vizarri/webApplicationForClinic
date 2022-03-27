package com.studentProject.webApplicationForClinic.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fio")
    private String Fio;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dateOfBirth")
    private String dateOfBirth;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "references")
    private String references;
}
