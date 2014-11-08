package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.CategoryDAO;
import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categories = session.createCriteria(Category.class).list();
        return categories;
    }

    @Override
    public Category getCategoryById(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.get(Category.class, category_id);
        return category;
    }

    @Override
    public Category getCategoryByUrlSlug(String categorySlug) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.createCriteria(Category.class)
                .add(Restrictions.eq("urlSlug", categorySlug))
                .uniqueResult();
        return category;
    }

    @Override
    public void removeCategory(int category_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.get(Category.class, category_id);
        if(category != null){
            session.delete(category);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPostsForCategory(Category category, int pageNumber, int postsPerPage) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery("from Post as post" +
                " where post.category = :category order by postedOnDate desc")
                .setEntity("category", category)
                .setFirstResult((pageNumber - 1) * postsPerPage)
                .setMaxResults(postsPerPage)
                .list();
        return posts;
    }
}
