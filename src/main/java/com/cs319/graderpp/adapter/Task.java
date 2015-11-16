package com.cs319.graderpp.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Task {

    private int taskId;
    private String courseCode;
    private String taskName;
    private String dueDate;
    private List<Submission> submissions;

    public Task(int taskId, String courseCode, String taskName, String dueDate) {
        this.taskId = taskId;
        this.courseCode = courseCode;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.submissions = new ArrayList<Submission>();
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public Submission getSubmissionFrom(Student student)
    {
        for(Submission submission :submissions)
        {
            if(submission.getSubmitter().getStudentId() == student.getStudentId())
                return submission;
        }
        return null;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
