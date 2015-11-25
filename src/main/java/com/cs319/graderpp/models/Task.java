package com.cs319.graderpp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Task {

    private int taskId;
    private Course course;
    private String taskName;
    private Date dueDate;
    private List<Submission> submissions;
    private List<MakeFile> makeFiles;
    private List<TestCaseFile> testCaseFiles;
    private Assistant assistant;

    public Task()
    {
        this.submissions = new ArrayList<Submission>();
        this.testCaseFiles = new ArrayList<TestCaseFile>();
        this.makeFiles = new ArrayList<MakeFile>();
    }

    public Task(Course course, String taskName, Date dueDate) {
        this.submissions = new ArrayList<Submission>();
        this.testCaseFiles = new ArrayList<TestCaseFile>();
        this.makeFiles = new ArrayList<MakeFile>();

        this.course = course;
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    public List<MakeFile> getMakeFiles() {
        return makeFiles;
    }

    public void setMakeFiles(List<MakeFile> makeFiles) {
        this.makeFiles = makeFiles;
    }

    public List<TestCaseFile> getTestCaseFiles() {
        return testCaseFiles;
    }

    public void setTestCaseFiles(List<TestCaseFile> testCaseFiles) {
        this.testCaseFiles = testCaseFiles;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
