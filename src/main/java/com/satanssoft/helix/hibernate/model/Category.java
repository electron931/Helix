package com.satanssoft.helix.hibernate.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "categories",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"category_id"})})
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="category_id", unique = true, nullable = false, length = 11)
    private int id;

    @Column(name="title", length = 50, unique = true, nullable = false)
    private String title;

    @Column(name="urlSlug", length = 50, unique = true, nullable = false)
    private String urlSlug;

    @Column(name="description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "categoryId")
    private List<Post> posts;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
