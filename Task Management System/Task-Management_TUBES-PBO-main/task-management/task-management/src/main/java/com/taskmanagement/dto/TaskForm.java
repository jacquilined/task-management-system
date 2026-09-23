package com.taskmanagement.dto;

import java.time.LocalDate;

//buat nampung data dari form tambah tugas
public class TaskForm {

    //buat nampung tipe tugas yang diinput user (aya)
    private String taskType;

    //buat nampung judul tugas yang diinput user (aya)
    private String title;

    //buat nampung deskripsi tugas yang diinput user (aya)
    private String description;

    //nyimpen deadline tugas yang diinput user (nesa)
    private LocalDate deadline;

    //buat nampung prioritas tugas yang diinput user (ofi)
    private String priority;

    //buat nampung status tugas yang diinput user (kirana)
    private String status;

    //buat nampung id kategori tugas yang diinput user (ofi)
    private Long categoryId;

    //buat nampung nama mata kuliah yang diinput user (aya)
    private String courseName;

    //constructor kosong buat objek TaskForm 
    public TaskForm() {
    }

    //getter dan setter buat tiap atribut diatas
    //buat ngambil tipe tugas (aya)
    public String getTaskType() {
        return taskType;
    }

    //buat nyimpen tipe tugas (aya)
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    //buat ngambil judul tugas (aya)
    public String getTitle() {
        return title;
    }

    //buat nyimpen judul tugas (aya)
    public void setTitle(String title) {
        this.title = title;
    }

    //buat ngambil deskripsi tugas (aya)
    public String getDescription() {
        return description;
    }

    //buat nyimpen deskripsi tugas (aya)
    public void setDescription(String description) {
        this.description = description;
    }

    //buat ngambil deadline tugas (nesa)
    public LocalDate getDeadline() {
        return deadline;
    }

    //buat nyimpen deadline tugas (nesa)
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    //buat ngambil prioritas tugas (ofi)
    public String getPriority() {
        return priority;
    }

    //buat nyimpen prioritas tugas (ofi)
    public void setPriority(String priority) {
        this.priority = priority;
    }

    //buat ngambil status tugas (kirana)
    public String getStatus() {
        return status;
    }

    //buat nyimpen status tugas (kirana)
    public void setStatus(String status) {
        this.status = status;
    }

    //buat ngambil id kategori tugas (ofi)
    public Long getCategoryId() {
        return categoryId;
    }

    //buat nyimpen id kategori tugas (ofi)
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    //buat ngambil nama mata kuliah (aya)
    public String getCourseName() {
        return courseName;
    }

    //buat nyimpen nama mata kuliah (aya)
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}