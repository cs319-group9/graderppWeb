package com.cs319.graderpp.adapter;

import com.cs319.graderpp.misc.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Student extends User {

    private int studentId;
    private List<Task> tasks;

    public Student(String username, String password, int studentId) {
        super(username, password, Constants.STUDENT);
        this.studentId = studentId;
        this.tasks = new ArrayList<Task>();

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
