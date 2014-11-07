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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
