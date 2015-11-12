package com.cs319.graderpp.adapter;

import com.cs319.graderpp.misc.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Student extends User {

    private int studentId;
    private List<String> courses;

    public Student(String username, String password, int studentId) {
        super(username, password, Constants.STUDENT);
        this.studentId = studentId;
        this.courses = new ArrayList<String>();
        this.courses.add("HUM111");
        this.courses.add("MATH101");
        this.courses.add("CS101");
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
