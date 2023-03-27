package com.art.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.portfolio.Model.Post;

public interface PostRepo extends JpaRepository<Post, Long>{
    
}
