package com.venn.po;


import java.util.ArrayList;
import java.util.List;

public class UserRole {

    User user;

    Role role;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
    }
}
