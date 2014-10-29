package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;

import java.util.List;

public interface PostDAO {

    public void addPost(Post post);
    public void updatePost(Post post);
    public List<Post> getAllPosts();
    public Post getPostById(int post_id);
    public void removePost(int post_id);
    public List<Comment> getAllCommentsForPost(Post post);
    public List<Tag> getAllTagsForPost(Post post);
    
}
