package com.art.portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.portfolio.Model.Tag;

public interface TagRepo extends JpaRepository <Tag, Long> {
    
}
