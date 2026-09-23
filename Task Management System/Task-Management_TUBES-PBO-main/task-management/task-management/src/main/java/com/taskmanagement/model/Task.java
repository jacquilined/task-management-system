package com.taskmanagement.model;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.interfaces.Validatable;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//nyimpen jenis tugas ACADEMIC/personal (aya)
@DiscriminatorColumn(name = "task_type")
public abstract class Task implements Validatable {

    //primary key tugas (aya)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nyimpen judul tugas (aya)
    @Column(nullable = false)
    private String title;

    //nyimpen deskripsi tugas (aya)
    private String description;

    //nyimpen dl tugas (nesa)
    @Column(nullable = false)
    private LocalDate deadline;

    //nyimpen prioritas (ofi)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    //nyimpen status tugas (kirana)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    //relaso tugas dengan user (kirana)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //relasi tugas dengan kategori (ofi)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //constructor kosong untuk membuat objek task (aya)
    public Task() {
    }

    //validasi biar judul tugas gaboleh kosong (aya)
    @Override
    public boolean validate() {
        return title != null && !title.isEmpty();
    }

    //buat ngambil id tugas (aya)
    public Long getId() {
        return id;
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

    //buat ngambil dl tugas (nesa)
    public LocalDate getDeadline() {
        return deadline;
    }

    //buat nyimpen dl tugas (nesa)
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // buat ngambil prioritas tugas (ofi)
    public Priority getPriority() {
        return priority;
    }

    //buat nyimpen prioritas tugas (ofi)
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    //buat nyimpen status (kirana)
    public TaskStatus getStatus() {
        return status;
    }

    //buat ngambil status (kirana)
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    //buat ngambil data user (kirana)
    public User getUser() {
        return user;
    }

    //buat nyimpen data user (kirana)
    public void setUser(User user) {
        this.user = user;
    }

    //buat ngambil dan nyimpen kategori tugas (ofi)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}