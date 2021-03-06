package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.Constants;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Instructor extends User {

    private List<Course> courses;

    public Instructor(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
