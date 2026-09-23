package com.taskmanagement.controller;

import com.taskmanagement.dto.TaskForm;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.TaskStatus;
import com.taskmanagement.model.AcademicTask;
import com.taskmanagement.model.Category;
import com.taskmanagement.model.PersonalTask;
import com.taskmanagement.model.Task;
import com.taskmanagement.service.CategoryService;
import com.taskmanagement.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

@Autowired
private TaskService taskService;

@Autowired
private CategoryService categoryService;

//jobdesk aya
//nampilin daftar tugas
@GetMapping("/tasks")
public String taskPage(

        @RequestParam(required = false)
        Priority priority,

        @RequestParam(required = false)
        Long categoryId,

        Model model) {

    if (priority != null) {

        model.addAttribute(
                "tasks",
                // buat nampilin tugas berdasarkan prioritas (ofi)
                taskService.getTasksByPriority(
                        priority
                )
        );

    } else if (categoryId != null) {

        model.addAttribute(
                "tasks",
                // buat nampilin tugas berdasarkan kategori (ofi)
                taskService.getTasksByCategory(
                        categoryId
                )
        );

    } else {

        model.addAttribute(
                "tasks",
                // buat nampilin semua tugas (aya)
                taskService.getAllTasks()
        );
    }

    model.addAttribute(
            "categories",
            categoryService.getAllCategories()
    );

    return "task";
}

// jobdesk aya
//buat nampilin halaman buat nambahin tugas baru
@GetMapping("/tasks/add")
public String addTaskPage(
        @RequestParam(required = false)
        String error,
        Model model) {

    model.addAttribute(
            "categories",
            categoryService.getAllCategories()
    );

    model.addAttribute(
            "taskForm",
            new TaskForm()
    );

    model.addAttribute(
            "error",
            error
    );

    return "add-task";
}

//jobdesk aya
//buat nampilin halaman buat ngedit tugas
@GetMapping("/tasks/edit/{id}")
public String editTaskPage(
        @PathVariable Long id,
        @RequestParam(required = false)
        String error,
        Model model) {

    Task task =
            taskService.getTaskById(id);

    model.addAttribute(
            "task",
            task
    );

    model.addAttribute(
            "categories",
            categoryService.getAllCategories()
    );

    model.addAttribute(
        "error",
        error
);
    return "edit-task";
}

// jobdesk aya
//buat nyimpen data tugas baru yg diinput user
@PostMapping("/tasks")
//spring otomatis ngambil data dari form dan dimasukin ke parameter TaskForm
public String saveTask(TaskForm form) {

        // user gabole masukkin deadline yang udah lewat (nesa)
    if (form.getDeadline().isBefore(
            java.time.LocalDate.now())) {

        return "redirect:/tasks/add?error=deadline";
    }

    Task task;

    // jobdesk aya
    // Menentukan jenis tugas berdasarkan input user
    if ("ACADEMIC".equals(form.getTaskType())) {

        AcademicTask academicTask =
                new AcademicTask();

        academicTask.setCourseName(
                form.getCourseName()
        );

        task = academicTask;

    } else {

        task = new PersonalTask();
    }
    task.setTitle(form.getTitle());

    task.setDescription(form.getDescription());

    // buat nyimpen deadline tugas (nesa)
    task.setDeadline(form.getDeadline());

    // buat nyimpen prioritas tugas (ofi)
    task.setPriority(
            Priority.valueOf(
                    form.getPriority()
            )
    );

    // buat nyimpen status tugas (kirana)
    task.setStatus(
            TaskStatus.valueOf(
                    form.getStatus()
            )
    );

    // buat nyimpen kategori tugas (ofi)
    Category category =
            categoryService.getCategoryById(
                    form.getCategoryId()
            );

    task.setCategory(category);

    taskService.save(task);

    return "redirect:/tasks";
}

// buat ngapus tugas (aya)
@GetMapping("/tasks/delete/{id}")
public String deleteTask(
        @PathVariable Long id) {

    taskService.deleteTask(id);

    return "redirect:/tasks";
}

// buat ngupdate tugas (aya)
@PostMapping("/tasks/update/{id}")
public String updateTask(
@PathVariable Long id,
@RequestParam String title,
@RequestParam String description,
@RequestParam java.time.LocalDate deadline,
@RequestParam Priority priority,
@RequestParam TaskStatus status,
@RequestParam Long categoryId) {

Task task =
        taskService.getTaskById(id);

if (task != null) {

        // user gabole masukkin deadline yang udah lewat (nesa)
    if (deadline.isBefore(
            java.time.LocalDate.now())) {

       return "redirect:/tasks/edit/" + id +
       "?error=deadline";
    }


    // BELUM_SELESAI -> SELESAI ngga boleh (kirana)
    if (task.getStatus() == TaskStatus.BELUM_SELESAI
            && status == TaskStatus.SELESAI) {

        return "redirect:/tasks/edit/" + id +
               "?error=workflow";
    }

    // PROGRESS -> BELUM_SELESAI tidak boleh (kirana)
    if (task.getStatus() == TaskStatus.PROGRESS
            && status == TaskStatus.BELUM_SELESAI) {

        return "redirect:/tasks/edit/" + id +
               "?error=workflow";
    }

    // SELESAI tidak boleh kembali ke status lain (kirana)
    if (task.getStatus() == TaskStatus.SELESAI
            && status != TaskStatus.SELESAI) {

        return "redirect:/tasks/edit/" + id +
               "?error=workflow";
    }

    // buat ngupdate judul tugas (aya)
    task.setTitle(title);

    // buat ngupdate deskripsi tugas (aya)
    task.setDescription(description);

    // buat ngupdate deadline tugas (nesa)
    task.setDeadline(deadline);

    // buat ngupdate prioritas tugas (ofi)
    task.setPriority(priority);

    // buat ngupdate status tugas (kirana)
    task.setStatus(status);

    Category category =
            categoryService.getCategoryById(
                    categoryId
            );

    // buat ngupdate kategori tugas (ofi)
    task.setCategory(category);

    // buat ngupdate tugas (aya)
    taskService.update(task);
}

return "redirect:/tasks";

}

}
