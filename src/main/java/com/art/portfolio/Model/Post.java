package com.art.portfolio.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;


@Table(name = "posts")
@Entity
public class Post {

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return this.postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostUrl() {
        return this.postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPostDescription() {
        return this.postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostPrice() {
        return this.postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String postTitle;

    @Column
    private String postUrl;

    @Column
    private String postDescription;

    @Column
    private String postPrice;

    @Column
    private String postDate;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Category> categories;

    @OneToMany
    private List<Tag> tags;

}
