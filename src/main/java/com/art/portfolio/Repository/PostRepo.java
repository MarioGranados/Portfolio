package com.art.portfolio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.art.portfolio.Model.Post;

public interface PostRepo extends JpaRepository<Post, Long>{
    List<Post> findByUserId(Long id);

    @Query(value = "SELECT * FROM posts WHERE post_image LIKE = !?", nativeQuery = true)
    List<Post> findAllByResults(@Param("name")String category);

    @Query(value = "select * from posts p left join categories c on p.post_id = c.category_id", nativeQuery = true)
    List<Post> findAllByCategories();

}
