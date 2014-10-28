package com.satanssoft.helix.hibernate.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tag_id"})})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "urlSlug", nullable = false)
    private String urlSlug;

    @ManyToMany(targetEntity = Post.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "tags_posts",
            joinColumns = { @JoinColumn(name = "tag_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private List<Post> posts;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
