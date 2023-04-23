package com.art.portfolio.Model;

import org.springframework.stereotype.Controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "categories")
@Entity
public class Category {

    Category() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Column
    String categoryName;

    @ManyToOne
    Post post;
    
}
