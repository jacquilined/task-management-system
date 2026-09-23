package com.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.taskmanagement.model.Category;
import com.taskmanagement.service.CategoryService;

// jobdesk ofi
@Controller
public class CategoryController {

// Menghubungkan controller dengan service kategori
@Autowired
private CategoryService categoryService;

// Menampilkan halaman kategori
@GetMapping("/categories")
public String categoryPage(Model model) {

    // Menyiapkan objek Category kosong
    // untuk form tambah kategori
    model.addAttribute(
            "category",
            new Category()
    );

    // Mengambil seluruh data kategori dari database
    // dan mengirimkannya ke halaman category.html
    model.addAttribute(
            "categories",
            categoryService.getAllCategories()
    );

    // Menampilkan halaman category.html
    return "category";
}

// Menyimpan kategori baru yang diinput user
@PostMapping("/categories")
public String saveCategory(Category category) {

    // Menyimpan kategori baru ke database
    categoryService.save(category);

    // setelah berhasil menyimpan, redirect ke halaman kategori
    return "redirect:/categories";
}

}
