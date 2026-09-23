package com.taskmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    //primary key user
    @Id
    // id dibuat otomatis sm database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nyimppen username user (kirana)
    @Column(nullable = false, unique = true)
    private String username;

    //nyimpen email user (kirana)
    @Column(nullable = false, unique = true)
    private String email;

    //nyimpen pw user (kirana)
    @Column(nullable = false)
    private String password;

    //nyimpen waktu regis (kirana)
    private LocalDateTime createdAt;

    //relasi 1 user bisa punya bnyk tugas (aya)
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    //relasi 1 user punya bnyk kategori
    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    //ngisi waktu regis scara otomatis saat data pertama kali disimpan(kirana)
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    //constructor kosong untuk membuat objek user (kirana)
    public User() {
    }

    //buat ngambil dan nyimpen id user (kirana)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //buat ngambil dan nyimpen username user (kirana)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //buat ngambil dan nyimpen email user (kirana)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //buat ngambil dan nyimpin pw user (kirana)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //buat ambil waktu regis user (kirana)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //buat ambl dan nyimpen daftar tugas milik user (aya)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    //buat ngambil dan nyimpen kategori milik user (ofi)
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}