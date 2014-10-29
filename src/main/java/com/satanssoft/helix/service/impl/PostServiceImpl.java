package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.PostDAO;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.service.PostService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    @Transactional
    public void addPost(Post post) {
        this.postDAO.addPost(post);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        this.postDAO.updatePost(post);
    }

    @Override
    @Transactional
    public List<Post> getAllPosts() {
        return this.postDAO.getAllPosts();
    }

    @Override
    @Transactional
    public Post getPostById(int post_id) {
        return this.postDAO.getPostById(post_id);
    }

    @Override
    @Transactional
    public void removePost(int post_id) {
        this.postDAO.removePost(post_id);
    }

    @Override
    @Transactional
    public List<Comment> getAllCommentsForPost(Post post) {
        return this.postDAO.getAllCommentsForPost(post);
    }

    @Override
    @Transactional
    public List<Tag> getAllTagsForPost(Post post) {
        return this.postDAO.getAllTagsForPost(post);
    }
}
