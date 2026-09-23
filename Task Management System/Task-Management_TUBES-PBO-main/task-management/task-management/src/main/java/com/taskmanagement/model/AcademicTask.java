package com.taskmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//jobdesk aya
@Entity
@DiscriminatorValue("ACADEMIC")
public class AcademicTask extends Task {

    //buat nyimpen nama mata kuliah (aya)
    @Column(nullable = true)
    private String courseName;

    //constructor kosong buat objek AcademicTask (aya)
    public AcademicTask() {
    }

    //buat ambil nama mata kuliah (aya)
    public String getCourseName() {
        return courseName;
    }

    //buat nyimpen nama mata kuliah (aya)
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}