package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.LazyLoading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Task {

    private String taskId;
    private String taskName;
    private Date dueDate;

    private Course course;
    private Assistant assistant;

    private List<Submission> submissions;
    private List<MakeFile> makeFiles;
    private List<TestCaseFile> testCaseFiles;

    public Task() {}

    public Task(String taskName, Date dueDate) {
        this.submissions = null;
        this.testCaseFiles = null;
        this.makeFiles = null;
        this.assistant = null;
        this.course = null;

        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object object)
    {
        if(((Task) object).getTaskId().equals(this.taskId))
            return true;
        return false;
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
            return this.submissions;
    }

    public Submission getSubmissionFrom(Student student) {
        for (Submission submission : submissions) {
            if (submission.getSubmitter().getStudentId() == student.getStudentId())
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
