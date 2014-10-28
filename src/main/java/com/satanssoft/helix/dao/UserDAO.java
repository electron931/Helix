package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);
    public void updateUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int user_id);
    public void removeUser(int user_id);
    public List<Post> getAllUserPosts(int user_id);

}
