package com.art.portfolio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.art.portfolio.Model.Post;

import jakarta.transaction.Transactional;

public interface PostRepo extends JpaRepository<Post, Long>{
    List<Post> findByUserId(Long id);

    @Query(value = "SELECT * FROM posts WHERE post_image LIKE = !?", nativeQuery = true)
    List<Post> findAllByResults(@Param("name")String category);

    @Query(value = "SELECT * FROM posts WHERE post_id = :id LIMIT 1", nativeQuery = true)
    Post findPostById(@Param("id") Long id);

    @Query(value = "SELECT * FROM posts WHERE user_id = :userId", nativeQuery = true)
    List<Post> findAllPostsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM posts WHERE user_id = :userId ORDER BY post_date", nativeQuery = true)
    List<Post> findAllPostsByUserOrderByDate(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM posts ORDER BY post_date", nativeQuery = true)
    List<Post> findAllPostsOrderByDate();

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "SELECT * FROM posts p LEFT JOIN categories c ON p.post_id = c.category_id WHERE c.category = :category", nativeQuery = true)
    List<Post> findAllPostsByCategories(@Param("category") String category);

}
