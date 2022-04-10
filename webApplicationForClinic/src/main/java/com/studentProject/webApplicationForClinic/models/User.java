package com.studentProject.webApplicationForClinic.models;

import com.studentProject.webApplicationForClinic.models.enums.Role;
import com.studentProject.webApplicationForClinic.models.enums.Status;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
