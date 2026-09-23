package com.taskmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.enums.Priority;
import java.time.LocalDate;
import java.util.Comparator;

@Service
public class TaskService {

//ngehubungin service dngn repository tugas
@Autowired
private TaskRepository taskRepository;

//nyimpen data tugas ke database (aya)
public Task save(Task task) {
    return taskRepository.save(task);
}

//ngambil seluruh data tugas dari database (aya)
public List<Task> getAllTasks() {
    return taskRepository.findAll();
}


//menghitung jumlah tugas yg udh selesai (devi)
public long getCompletedTaskCount() {

    return taskRepository.findAll()
            .stream()
            .filter(task ->
                    task.getStatus() == TaskStatus.SELESAI)
            .count();
}

//menghitung jumlah tugas yg sedang dikerjakan (devi)
public long getProgressTaskCount() {

    return taskRepository.findAll()
            .stream()
            .filter(task ->
                    task.getStatus() == TaskStatus.PROGRESS)
            .count();
}

//ngehitung jumlah tugas yg blm dikerjakan (devi)
public long getPendingTaskCount() {

    return taskRepository.findAll()
            .stream()
            .filter(task ->
                    task.getStatus() == TaskStatus.BELUM_SELESAI)
            .count();
}

//ngambil data tugas berdasarkan id (aya)
public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElse(null);
}

//mengupdate data tugas (aya)
public Task update(Task task) {
    return taskRepository.save(task);
}

//menghapus data tugas (aya)
public void deleteTask(Long id) {
    taskRepository.deleteById(id);
}

//mengambil tugas dengan prioritas tinggi (devi) di monitoring
public List<Task> getHighPriorityTasks() {

    return taskRepository.findByPriority(
            Priority.HIGH
    );

    }

//ngurutin tugas berdasarkan deadline terdekat (nesa) di monitoring
public List<Task> getTasksByDeadline() {

    return taskRepository.findAll()
            .stream()
            .sorted(
                    Comparator.comparing(
                            Task::getDeadline
                    )
            )
            .toList();
}

//ngambil tugas yg udh lewat dl (nesa) di monitoring
public List<Task> getOverdueTasks() {

    return taskRepository.findAll()
            .stream()
            .filter(task ->
                    task.getDeadline().isBefore(
                            LocalDate.now()
                    )
                    &&
                    task.getStatus()
                            != TaskStatus.SELESAI)
            .toList();
}

//ngambil tugas yg dl nya dlm 3 hari kedepan (nesa) di monitoring
public List<Task> getUpcomingTasks() {

    LocalDate today =
            LocalDate.now();

    LocalDate nextThreeDays =
            today.plusDays(3);

    return taskRepository.findAll()
            .stream()
            .filter(task ->

                    !task.getDeadline()
                            .isBefore(today)

                    &&

                    !task.getDeadline()
                            .isAfter(nextThreeDays)

                    &&

                    task.getStatus()
                            != TaskStatus.SELESAI
            )
            .toList();
    }

//ngambil tugas berdasarkan prioritas (ofi)
public List<Task> getTasksByPriority(
        Priority priority) {

    return taskRepository.findByPriority(
            priority
    );
}

//ngambil tugas berdasarkan kategori (ofi)
public List<Task> getTasksByCategory(
        Long categoryId) {

    return taskRepository.findByCategoryId(
            categoryId
    );
}
}