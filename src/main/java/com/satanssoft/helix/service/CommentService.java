package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.User;

import java.util.List;

public interface CommentService {

    public int addComment(Comment comment);
    public void updateComment(Comment comment);
    public Comment getCommentById(int comment_id);
    public void removeComment(int comment_id);
    public List<Comment> getAllUserComments(User user);
    public List<Comment> getAllComments();

}
