package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.CommentDAO;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.User;
import com.satanssoft.helix.service.CommentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional
    public int addComment(Comment comment) {
        return this.commentDAO.addComment(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        this.commentDAO.updateComment(comment);
    }

    @Override
    @Transactional
    public Comment getCommentById(int comment_id) {
        return this.commentDAO.getCommentById(comment_id);
    }

    @Override
    @Transactional
    public void removeComment(int comment_id) {
        this.commentDAO.removeComment(comment_id);
    }

    @Override
    @Transactional
    public List<Comment> getAllUserComments(User user) {
        return this.commentDAO.getAllUserComments(user);
    }

    @Override
    @Transactional
    public List<Comment> getAllComments() {
        return this.commentDAO.getAllComments();
    }
}
