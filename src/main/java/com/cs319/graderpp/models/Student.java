package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Student extends User {

    private int studentId;
    private List<Submission> submissions;
    private List<Course> courses;

    public Student(String username, String password, int studentId, String fullName) {
        super(username, password, fullName);
        this.studentId = studentId;
        this.courses = new ArrayList<Course>();
        this.submissions = new ArrayList<Submission>();

    }

    public List<Task> getAssignedTasks() {
        List<Task> tasks = new ArrayList<Task>();
        for (Course course : courses) {
            if (course.getTasks().size() > 0) {
                tasks.addAll(course.getTasks());
            }
        }
        return tasks;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}
