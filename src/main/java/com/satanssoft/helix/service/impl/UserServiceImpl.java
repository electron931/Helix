package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.UserDAO;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.User;
import com.satanssoft.helix.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(int user_id) {
        return this.userDAO.getUserById(user_id);
    }

    @Override
    @Transactional
    public User getUserByName(String userName) {
        return this.userDAO.getUserByName(userName);
    }

    @Override
    @Transactional
    public void removeUser(int user_id) {
        this.userDAO.removeUser(user_id);
    }

    @Override
    @Transactional
    public List<Post> getAllUserPosts(User user, int pageNumber, int postsPerPage) {
        return this.userDAO.getAllUserPosts(user, pageNumber, postsPerPage);
    }
}
