package com.devm8.stockalarms.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
public class Role {

    @Id
    private Long id;

    @Column(name = "rolename")
    private String roleName;

    @Transient
    private Set<MyUser> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<MyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MyUser> users) {
        this.users = users;
    }
}
