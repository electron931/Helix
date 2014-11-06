package com.satanssoft.helix.hibernate.dao;

import com.satanssoft.helix.dao.RoleDAO;
import com.satanssoft.helix.hibernate.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public void addRole(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(role);
    }

    @Override
    public Role getRole(int role_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.get(Role.class, role_id);
        return role;
    }
}
