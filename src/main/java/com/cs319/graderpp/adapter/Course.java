package com.cs319.graderpp.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 17.11.2015.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private List<Task> tasks;
    private List<Instructor> instructors;
    private List<Student> students;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.tasks = new ArrayList<Task>();
        this.instructors = new ArrayList<Instructor>();
        this.students = new ArrayList<Student>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task)
    {
        this.tasks.add(task);
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
