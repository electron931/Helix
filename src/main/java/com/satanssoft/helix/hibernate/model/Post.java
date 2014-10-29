package com.satanssoft.helix.hibernate.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"post_id"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "title", length = 50, unique = true, nullable = false)
    private String title;

    @Column(name = "shortDescription", nullable = false)
    @Type(type = "text")
    private String shortDescription;

    @Column(name = "description", nullable = false)
    @Type(type = "text")
    private String description;

    @Column(name = "urlSlug", unique = true, nullable = false)
    private String urlSlug;

    @Column(name = "thumbnail", nullable = true)
    private String thumbnail;

    @Column(name = "isPublished", nullable = false)
    private boolean isPublished;

    @Column(name = "postedOnDate", nullable = false)
    private Date postedOnDate;

    @Column(name = "modifiedDate", nullable = true)
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)        //for testing!!!
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    //Hibernate requires no-args constructor
    public Post() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Date getPostedOnDate() {
        return postedOnDate;
    }

    public void setPostedOnDate(Date postedOnDate) {
        this.postedOnDate = postedOnDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
