package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);
    public void updateUser(User user);
    public List<User> getAllUsers();
    public List<User> getAllModerators();
    public User getUserById(int user_id);
    public User getUserByName(String userName);
    public void removeUser(int user_id);
    public List<Post> getAllUserPosts(User user);
    public List<Post> getAllUserPosts(User user, int pageNumber, int postsPerPage);

}
