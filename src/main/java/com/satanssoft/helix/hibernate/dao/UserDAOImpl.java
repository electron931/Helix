package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.UserDAO;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    @Override
    public User getUserById(int user_id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, user_id);
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("userName", userName))
                .uniqueResult();
        return user;
    }

    @Override
    public void removeUser(int user_id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, user_id);
        if(user != null){
            session.delete(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllUserPosts(User user, int pageNumber, int postsPerPage) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery("from Post as post where post.author = :author")
                .setEntity("author", user)
                .setFirstResult((pageNumber - 1) * postsPerPage)
                .setMaxResults(postsPerPage)
                .list();
        return posts;
    }

}
