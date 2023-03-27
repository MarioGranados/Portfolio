package com.art.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.portfolio.Model.User;

public interface UserRepo extends JpaRepository <User, Long>{
    User findByUsername(String username);

}