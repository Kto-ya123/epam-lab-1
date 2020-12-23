package com.company.model.dao;

/**
 *  User.
 *
 * @author Artsiom Mazhylouski
 */
public class User {
    private String email;
    private String password;
    private String userName;
    private Role role;

    public User(String email, String password, String userName, Role role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
