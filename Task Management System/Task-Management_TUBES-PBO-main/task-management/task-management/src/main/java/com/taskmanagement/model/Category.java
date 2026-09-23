package com.taskmanagement.model;

import jakarta.persistence.*;
import java.util.List;

//jobdesk ofi
@Entity
//menentukan nama tabel di database
@Table(name = "categories")
public class Category {

    //primary key kategori
    @Id
    //id dibuat otomatis sama database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //menyimpan nama kategori
    @Column(nullable = false)
    private String name;

    @ManyToOne
    //foreign key yg ngehubungin ke tabel user
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    //constructor kosong untuk membuat objek kategori 
    public Category() {
    }

    //buat ngambil id kategori
    public Long getId() {
        return id;
    }

    //buat ngambil nama kategori
    public String getName() {
        return name;
    }

    //buat nyimpem nama kategori
    public void setName(String name) {
        this.name = name;
    }

    //buat ngambil data user
    public User getUser() {
        return user;
    }

    //buat nyimpen data user
    public void setUser(User user) {
        this.user = user;
    }

    //buat ngambil daftar tugas dalam kategori 
    public List<Task> getTasks() {
        return tasks;
    }

    //buat nyimpen daftar tugas dalam kategori 
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}