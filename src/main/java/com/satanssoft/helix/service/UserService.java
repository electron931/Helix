package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);
    public void updateUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int user_id);
    public void removeUser(int user_id);
    public List<Post> getAllUserPosts(User user, int pageNumber, int postsPerPage);

}
