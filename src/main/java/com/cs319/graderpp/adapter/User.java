package com.cs319.graderpp.adapter;

import com.cs319.graderpp.service.GraderppServiceImpl;

/**
 * Created by burak on 07.11.2015.
 */
public class User {
    private String username;
    private String password;
    private int userType;
    private int userId;

    public User(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
