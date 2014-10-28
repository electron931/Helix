package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.CategoryDAO;
import com.satanssoft.helix.hibernate.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(category);
    }

    @Override
    public void updateCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(category);
    }

    @Override
    public List<Category> getAllCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categories = session.createQuery("from category").list();
        return categories;
    }

    @Override
    public Category getCategoryById(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, new Integer(category_id));
        return category;
    }

    @Override
    public void removeCategory(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, new Integer(category_id));
        if(category != null){
            session.delete(category);
        }
    }

}
