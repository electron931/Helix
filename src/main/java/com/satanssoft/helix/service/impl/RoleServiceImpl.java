package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.RoleDAO;
import com.satanssoft.helix.hibernate.model.Role;
import com.satanssoft.helix.service.RoleService;
import org.springframework.transaction.annotation.Transactional;


public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    @Transactional
    public void addRole(Role role) {
        this.roleDAO.addRole(role);
    }

    @Override
    @Transactional
    public Role getRole(int role_id) {
        return this.roleDAO.getRole(role_id);
    }
}
