package com.studentProject.webApplicationForClinic.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students",schema = "public")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "reference")
    private String reference;
}
