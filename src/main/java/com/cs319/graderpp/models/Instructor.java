package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.LazyLoading;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Instructor extends User {

    private List<Course> courses;

    public Instructor(String username, String password, String fullName) {
        super(username, password, fullName);
        courses = null;
    }

    @Override
    public boolean equals(Object object)
    {
        if(((Instructor) object).getUserId().equals(this.getUserId()))
            return true;
        return false;
    }

    public List<Course> getCourses() {
            return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
