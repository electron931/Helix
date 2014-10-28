package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Comment;

import java.util.List;

public interface CommentDAO {

    public void addComment(Comment comment);
    public void updateComment(Comment comment);
    public List<Comment> getAllUserComments(int user_id);
    public Comment getCommentById(int comment_id);
    public void removeComment(int comment_id);
    
}
