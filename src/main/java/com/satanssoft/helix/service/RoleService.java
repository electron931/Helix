package com.satanssoft.helix.service;

import com.satanssoft.helix.hibernate.model.Role;


public interface RoleService {

    public void addRole(Role role);
    public Role getRole(int role_id);

}
