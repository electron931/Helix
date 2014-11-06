package com.satanssoft.helix;

import java.util.ArrayList;
import java.util.List;

import com.satanssoft.helix.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        com.satanssoft.helix.hibernate.model.User domainUser = userDAO.getUserByName(userName);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                domainUser.getUserName(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRole().getId())
        );
    }

    public List<SimpleGrantedAuthority> getAuthorities(int role_id) {
        return getGrantedAuthorities(getRoles(role_id));
    }

    public List<String> getRoles(int role_id) {

        List<String> roles = new ArrayList<String>();

        if (role_id == 1) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_ADMIN");
        } else if (role_id == 2) {
            roles.add("ROLE_MODERATOR");
        }

        return roles;
    }

    public static List<SimpleGrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}
