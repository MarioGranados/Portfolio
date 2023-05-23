package com.art.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.art.portfolio.Model.User;

public interface UserRepo extends JpaRepository <User, Long>{
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery =  true)
    User findByEmail(@RequestParam(name = "email") String email);

}