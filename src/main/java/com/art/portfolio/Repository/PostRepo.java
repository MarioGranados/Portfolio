package com.art.portfolio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.portfolio.Model.Post;

public interface PostRepo extends JpaRepository<Post, Long>{
    List<Post> findByUserId(Long id);
}
