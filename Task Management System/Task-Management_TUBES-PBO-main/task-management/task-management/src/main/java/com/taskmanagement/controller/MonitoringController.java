package com.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.taskmanagement.service.TaskService;

@Controller
public class MonitoringController {

        // Menghubungkan controller dengan service task
    @Autowired
    private TaskService taskService;

    // jobdesk devi
    // Menampilkan halaman monitoring
    @GetMapping("/monitoring")
    public String monitoringPage(Model model) {

        // Mengambil data statistik tugas dari service
        //menghtung smua tugas
        long totalTasks =
                taskService.getAllTasks().size();

        //menghtung tugas yg masih dikerjain
        long completedTasks =
                taskService.getCompletedTaskCount();

        //menghtung tugas yg sedang dikerjain
        long progressTasks =
                taskService.getProgressTaskCount();

        //menghtung tugas yg belum dikerjain
        long pendingTasks =
                taskService.getPendingTaskCount();

        double completionPercentage = 0;


        if (totalTasks > 0) {

            completionPercentage =
                    ((double) completedTasks
                            / totalTasks) * 100;
        }

        // Menambahkan data statistik ke model untuk dikirim ke halaman monitoring.html
        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("progressTasks", progressTasks);
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("completionPercentage", completionPercentage);

        //menampilkan tugas dengan prioritas tinggi
        model.addAttribute(
                "highPriorityTasks",
                taskService.getHighPriorityTasks()
        );

        //menampilkan seluruh tugas
        model.addAttribute(
                "tasks",
                taskService.getAllTasks()
        );


        // jobdesk nesa 

        //ngambil dri task service buat ditampilin di halaman monitoring.html
        //menampilkan tugas yg udh lewaat dl  
        model.addAttribute(
                "overdueTasks",
                taskService.getOverdueTasks()
        );

        //menampilkan tugas yg akan datang (dlnya)
        model.addAttribute(
                "upcomingTasks",
                taskService.getUpcomingTasks()
        );

        //menampilkan tugas yg dikelompokan berdasarkan deadline
        model.addAttribute(
                "tasksByDeadline",
                taskService.getTasksByDeadline()
        );
        return "monitoring";
        
    }
}