package com.satanssoft.helix.hibernate.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id"})})
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "role", length = 50, unique = true, nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;


    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
