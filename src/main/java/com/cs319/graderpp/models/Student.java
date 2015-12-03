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

    public Student(String username, String password, String fullName) {
        super(username, password, fullName);
        //this.courses = new ArrayList<Course>();
        //this.submissions = new ArrayList<Submission>();

    }

    public List<Task> getAssignedTasks() {
        List<Task> tasks = new ArrayList<Task>();
        for (Course course : getCourses() ) {
            if (course.getTasks().size() > 0) {
                tasks.addAll(course.getTasks());
            }
        }
        return tasks;
    }

    public List<Course> getCourses() {
        if(courses != null)
            return this.courses;

        return LazyLoading.getCoursesOfUser(this);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Submission> getSubmissions() {
        if(submissions != null)
            return this.submissions;

        return LazyLoading.getSubmissionsOfStudent(this);
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

}
