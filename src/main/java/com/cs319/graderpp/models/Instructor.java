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
    }

    public List<Course> getCourses() {
        if(courses != null)
            return this.courses;

        return LazyLoading.getCoursesOfUser(this);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
