package com.cs319.graderpp.service;

import com.cs319.graderpp.adapter.Instructor;
import com.cs319.graderpp.adapter.Student;
import com.cs319.graderpp.adapter.User;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service(value = "graderppService")
public class GraderppServiceImpl implements GraderppService {

    private List<User> userList;
    public enum UserType { STUDENT, INSTRUCTOR, ASSISTANT };

    @PostConstruct
    public void init()
    {
        userList = prepareUserList();
    }

    private List<User> prepareUserList()
    {
        List<User> users = new ArrayList<User>();
        User u1 = new Student("student", "password", 123);
        User u2 = new Instructor("instructor", "password");
        users.add(u1);
        users.add(u2);
        return users;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}