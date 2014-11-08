package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.PostDAO;
import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PostDAOImpl implements PostDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addPost(Post post) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void updatePost(Post post) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPosts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session
                .createCriteria(Post.class)
                .addOrder(Order.desc("postedOnDate"))
                .list();
        return posts;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getPostsForPage(int pageNumber, int postsPerPage) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery("from Post order by postedOnDate desc")
                .setFirstResult((pageNumber - 1) * postsPerPage)
                .setMaxResults(postsPerPage)
                .list();
        return posts;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPostsForSearch(String search, int pageNumber, int postsPerPage) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Post> posts = session.createQuery("from Post where title like :title or " +
                "shortDescription like :shortDescription or " +
                "description like :description order by postedOnDate desc")
                .setString("title", "%" + search + "%")
                .setString("shortDescription", "%" + search + "%")
                .setString("description", "%" + search + "%")
                .setFirstResult( (pageNumber - 1) * postsPerPage )
                .setMaxResults(postsPerPage)
                .list();
        return posts;
    }

    @Override
    public Post getPostById(int post_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, post_id);
        return post;
    }

    @Override
    public Post getPostByUrlSlug(String urlSlug) {
        Session session = this.sessionFactory.getCurrentSession();
        Post post = (Post) session.createCriteria(Post.class)
                .add(Restrictions.eq("urlSlug", urlSlug))
                .uniqueResult();
        return post;
    }

    @Override
    public void removePost(int post_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, post_id);
        if(post != null){
            String sql = "DELETE FROM comments WHERE post_id='" + post.getId() + "';";
            session.createSQLQuery(sql).executeUpdate();
            session.delete(post);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getAllCommentsForPost(Post post) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Comment> comments = session.createQuery("from Comment as comment" +
                " where comment.post = :post order by createdDate desc")
                .setEntity("post", post)
                .list();
        return comments;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tag> getAllTagsForPost(Post post) {
        String hql = "select u from Tag u inner join u.posts r where r.id =:id";
        Session session = this.sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery(hql)
                .setInteger("id", post.getId())
                .list();
        return tags;
    }

}
