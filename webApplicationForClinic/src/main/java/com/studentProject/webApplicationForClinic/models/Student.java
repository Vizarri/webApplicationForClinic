package com.studentProject.webApplicationForClinic.models;


public class Student {

    private int id;

    private String name;


    private String surname;


    private String dateOfBirth;


    private String faculty;


    private String references;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getReferences() {
        return this.references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

}
