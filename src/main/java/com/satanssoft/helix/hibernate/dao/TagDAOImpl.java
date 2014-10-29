package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.TagDAO;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TagDAOImpl implements TagDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void addTag(Tag tag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(tag);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tag> getAllTags() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Tag> tags = session.createCriteria(Tag.class).list();
        return tags;
    }

    @Override
    public Tag getTagById(int tag_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Tag tag = (Tag) session.get(Tag.class, tag_id);
        return tag;
    }

    @Override
    public void removeTag(int tag_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Tag tag = (Tag) session.get(Tag.class, tag_id);
        if(tag != null){
            session.delete(tag);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPostsForTag(Tag tag) {
        return null;
    }
    
}
