package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;

import java.util.List;

public interface CategoryService {

    public void addCategory(Category category);
    public void updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int category_id);
    public Category getCategoryByUrlSlug(String categorySlug);
    public void removeCategory(int category_id);
    public List<Post> getAllPostsForCategory(Category category, int pageNumber, int postsPerPage);

}
