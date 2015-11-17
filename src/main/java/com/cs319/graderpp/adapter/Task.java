package com.cs319.graderpp.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Task {

    private int taskId;
    private Course course;
    private String taskName;
    private String dueDate;
    private List<Submission> submissions;
    private Assistant assistant;

    public Task(int taskId, Course course, String taskName, String dueDate) {
        this.taskId = taskId;
        this.course = course;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.submissions = new ArrayList<Submission>();
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
