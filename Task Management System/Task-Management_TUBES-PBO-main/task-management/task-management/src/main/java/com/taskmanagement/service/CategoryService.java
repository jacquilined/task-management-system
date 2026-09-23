package com.taskmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.Category;
import com.taskmanagement.repository.CategoryRepository;

//jobdesk ofi
//service buat ngelola data ke kategori
@Service
public class CategoryService {

    //ngehubungin service dengan repository kategori 
    @Autowired
    private CategoryRepository categoryRepository;

    //menyimpan data kategori ke database
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    //mengambil seluruh data kategori dari database
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //ngambil data kategori berdasarkan id 
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}