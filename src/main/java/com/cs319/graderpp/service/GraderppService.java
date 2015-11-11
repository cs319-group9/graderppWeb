package com.cs319.graderpp.service;

import com.cs319.graderpp.adapter.User;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public interface GraderppService {
    public List<User> getUserList();
    public void setUserList(List<User> userList);
}
