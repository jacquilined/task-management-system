package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.model.Category;

//repository buat ngakses data kategori di database (ofi)
public interface CategoryRepository extends JpaRepository<Category, Long> {

}