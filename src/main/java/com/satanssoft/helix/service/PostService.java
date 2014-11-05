package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;

import java.util.List;

public interface PostService {

    public void addPost(Post post);
    public void updatePost(Post post);
    public List<Post> getAllPosts();
    public List<Post> getPostsForPage(int pageNumber, int postsPerPage);
    public Post getPostById(int post_id);
    public Post getPostByUrlSlug(String urlSlug);
    public void removePost(int post_id);
    public List<Comment> getAllCommentsForPost(Post post);
    public List<Tag> getAllTagsForPost(Post post);

}
