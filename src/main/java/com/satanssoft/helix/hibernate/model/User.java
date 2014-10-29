package com.satanssoft.helix.hibernate.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"})})
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "userName", length = 50, unique = true, nullable = false)
    private String userName;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "type", length = 10, nullable = false)
    private String type;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
