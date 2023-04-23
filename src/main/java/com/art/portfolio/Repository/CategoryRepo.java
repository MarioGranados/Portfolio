package com.art.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.portfolio.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    
}
