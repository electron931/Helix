package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.CommentDAO;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public int addComment(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        int id = (Integer) session.save(comment);
        return id;
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(comment);
    }

    @Override
    public Comment getCommentById(int comment_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.get(Comment.class, comment_id);
        return comment;
    }

    @Override
    public void removeComment(int comment_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.get(Comment.class, comment_id);
        if(comment != null){
            session.delete(comment);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getAllUserComments(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Comment> comments = session.createCriteria(Comment.class)
                .add(Restrictions.like("user", user))
                .list();
        return comments;
    }

}
