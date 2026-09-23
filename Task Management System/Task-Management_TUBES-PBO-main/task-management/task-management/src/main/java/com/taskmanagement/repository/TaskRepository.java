package com.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.model.Task;

//repository buat ngakses data tugas di database
public interface TaskRepository extends JpaRepository<Task, Long> {

    // cari tugas berdasarkan prioritas (ofi)
    List<Task> findByPriority(Priority priority);

    //cari tugas berdasarkan kategori (ofi)
    List<Task> findByCategoryId(Long categoryId);
}