package com.company.model.authentication;

import com.company.model.dao.Role;

public class AuthenticationUser {
    private String userName;
    private Role role;

    public AuthenticationUser(String userName, Role role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
