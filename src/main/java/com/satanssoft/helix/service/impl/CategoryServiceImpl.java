package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.CategoryDAO;
import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        this.categoryDAO.addCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        this.categoryDAO.updateCategory(category);
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return this.categoryDAO.getAllCategories();
    }

    @Override
    @Transactional
    public Category getCategoryById(int category_id) {
        return this.categoryDAO.getCategoryById(category_id);
    }

    @Override
    @Transactional
    public Category getCategoryByUrlSlug(String categorySlug) {
        return this.categoryDAO.getCategoryByUrlSlug(categorySlug);
    }

    @Override
    @Transactional
    public void removeCategory(int category_id) {
        this.categoryDAO.removeCategory(category_id);
    }

    @Override
    @Transactional
    public List<Post> getAllPostsForCategory(Category category, int pageNumber, int postsPerPage) {
        return this.categoryDAO.getAllPostsForCategory(category, pageNumber, postsPerPage);
    }
}
