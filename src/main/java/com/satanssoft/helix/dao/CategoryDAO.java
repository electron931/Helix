package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Category;

import java.util.List;

public interface CategoryDAO {

    public void addCategory(Category category);
    public void updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int category_id);
    public void removeCategory(int category_id);

}
