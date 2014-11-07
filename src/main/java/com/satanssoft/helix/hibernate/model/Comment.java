package com.satanssoft.helix.hibernate.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comments",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"comment_id"})})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "text", nullable = false)
    @Type(type = "text")
    private String text;

    @Column(name = "userName", nullable = false)
    @Type(type = "text")
    private String userName;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    //Hibernate requires no-args constructor
    public Comment() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
