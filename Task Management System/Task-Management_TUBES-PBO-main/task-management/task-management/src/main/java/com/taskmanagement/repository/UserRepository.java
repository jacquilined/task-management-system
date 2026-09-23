package com.taskmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.model.User;

//jobdesk kirana
//repository buat ngakses data user di database
public interface UserRepository extends JpaRepository<User, Long> {

    //mcari user berdasarkan email 
    Optional<User> findByEmail(String email);

    //mcari user berdasarkan username
    Optional<User> findByUsername(String username);

}