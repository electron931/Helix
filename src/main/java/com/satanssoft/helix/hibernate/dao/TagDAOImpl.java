package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.TagDAO;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
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
    public Tag getTagByUrlSlug(String tagSlug) {
        Session session = this.sessionFactory.getCurrentSession();
        Tag tag = (Tag) session.createCriteria(Tag.class)
                .add(Restrictions.eq("urlSlug", tagSlug))
                .uniqueResult();
        return tag;
    }

    @Override
    public void removeTag(int tag_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Tag tag = (Tag) session.get(Tag.class, tag_id);
        if(tag != null){
            String sql = "DELETE FROM posts_tags WHERE tag_id='" + tag.getId() + "';";
            session.createSQLQuery(sql).executeUpdate();
            session.delete(tag);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPostsForTag(Tag tag, int pageNumber, int postsPerPage) {
        String hql = "select u from Post u inner join u.tags r" +
                " where r.id =:id order by postedOnDate desc";
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery(hql)
                .setInteger("id", tag.getId())
                .setFirstResult((pageNumber - 1) * postsPerPage)
                .setMaxResults(postsPerPage)
                .list();
        return posts;
    }
    
}
