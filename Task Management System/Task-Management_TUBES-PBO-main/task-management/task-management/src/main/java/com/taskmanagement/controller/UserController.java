package com.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.model.User;
import com.taskmanagement.service.TaskService;
import com.taskmanagement.service.UserService;

@Controller
public class UserController {

@Autowired
private UserService userService;

@Autowired
private TaskService taskService;

//buat nampilin halaman home (kirana)
@GetMapping("/")
public String home() {
    return "index";
}

//buat nampilin halaman login (kirana)
@GetMapping("/login")
public String loginPage() {
    return "login";
}

//buat nampilin halaman register (kirana)
@GetMapping("/register")
public String registerPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
}

//proses register user (kirana)
@PostMapping("/register")
public String register(User user) {

    userService.register(user);

    return "redirect:/login";
}

//dashboard statistik tugas (devi)
@GetMapping("/dashboard")
public String dashboard(Model model) {

        // mengambil hasil perhitungan jumlah tugas dari TaskService. (devi)
    long totalTasks =
            taskService.getAllTasks().size();

          //mengambil hasil perhitungan jumlah tugas selesai dari TaskService. (devi)
    long completedTasks =
            taskService.getCompletedTaskCount();

            // mengambil hasil perhitungan jumlah tugas yang sedang dikerjakan dari TaskService. (devi)
    long progressTasks =
            taskService.getProgressTaskCount();

            // mengambil hasil perhitungan jumlah tugas yang belum dikerjakan dari TaskService. (devi)
    long pendingTasks =
            taskService.getPendingTaskCount();

            //kirim data ke halaman dashboard (devi)
    model.addAttribute(
            "totalTasks",
            totalTasks
    );

    model.addAttribute(
            "completedTasks",
            completedTasks
    );

    model.addAttribute(
            "progressTasks",
            progressTasks
    );

    model.addAttribute(
            "pendingTasks",
            pendingTasks
    );

    return "dashboard";
}

// proses login user (kirana)
@PostMapping("/login")
public String login(
        @RequestParam String email,
        @RequestParam String password) {

    User user = userService.findByEmail(email);

    if (user != null) {

        //buat objek BCrypt untuk verifikasi password (kirana)
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        //verifikasi password yang diinput user dengan password yang tersimpan di database (kirana)
        if (encoder.matches(
                password,
                user.getPassword())) {

            return "redirect:/dashboard";
        }
    }

    return "login";
}

}
