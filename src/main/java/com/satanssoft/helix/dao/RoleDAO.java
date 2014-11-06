package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Role;


public interface RoleDAO {

    public void addRole(Role role);
    public Role getRole(int role_id);

}
