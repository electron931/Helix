package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.PostDAO;
import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PostDAOImpl implements PostDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addPost(Post post) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void updatePost(Post post) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPosts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createCriteria(Post.class).list();
        return posts;
    }

    @Override
    public Post getPostById(int post_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Category.class, post_id);
        return post;
    }

    @Override
    public void removePost(int post_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, post_id);
        if(post != null){
            session.delete(post);
        }
    }

    @Override
    public List<Comment> getAllCommentsForPost(Post post) {
        return null;
    }

    @Override
    public List<Tag> getAllTagsForPost(Post post) {
        return null;
    }

}
