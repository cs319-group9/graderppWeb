package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.LazyLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 17.11.2015.
 */
public class Course {
    private String courseId;
    private String courseCode;
    private String courseName;
    private List<Task> tasks;
    private List<Instructor> instructors;
    private List<Student> students;

    public Course() {}

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.tasks = null;
        this.instructors = null;
        this.students = null;
    }

    @Override
    public boolean equals(Object object)
    {
        if(((Course) object).getCourseId().equals(this.courseId) )
            return true;
        return false;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
