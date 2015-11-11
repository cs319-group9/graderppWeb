package com.cs319.graderpp.adapter;

/**
 * Created by burak on 07.11.2015.
 */
public class Student extends User {

    private int studentId;

    public Student(String username, String password, int studentId) {
        super(username, password, 1);
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
