package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.LazyLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Student extends User {

    private String studentId;
    private List<Submission> submissions;
    private List<Course> courses;
    private List<Task> tasks;


    public Student(String username, String password, String fullName) {
        super(username, password, fullName);
        this.courses = null;
        this.submissions = null;
        this.tasks = null;
    }

    @Override
    public boolean equals(Object object)
    {
        if(((Student) object).getUserId().equals(this.getUserId()))
            return true;
        return false;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Course> getCourses() {
        return this.courses;
    }


    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Submission> getSubmissions() {
            return this.submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /*public List<Task> getAssignedTasks() {
        List<Task> tasks = new ArrayList<Task>();

        if( getCourses() == null )
            return null;

        for (Course course : getCourses() ) {
            // if this course's tasks is not set yet, return null
            if( course.getTasks() == null)
                return null;

            if (course.getTasks().size() > 0) {
                tasks.addAll(course.getTasks());
            }
        }
        return tasks;

    }*/

}
