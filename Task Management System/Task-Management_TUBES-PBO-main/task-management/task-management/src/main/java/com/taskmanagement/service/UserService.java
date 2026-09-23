package com.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.User;
import com.taskmanagement.repository.UserRepository;

//jobdesk kirana
//service buat ngelola data user
@Service
public class UserService {

    //menghubungkan service dngn repository user
    @Autowired
    private UserRepository userRepository;

    //objek buat mengenkripsikan pw pakai BCrypt
    private BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

            //daftarin suer baru ke database
    public User register(User user) {

        //mengenkripsi pw sblm disimpan ke database
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        //nyimpen data user ke database
        return userRepository.save(user);
    }

    //cari user berdasarkan email 
     public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}