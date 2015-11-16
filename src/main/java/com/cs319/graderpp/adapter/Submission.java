package com.cs319.graderpp.adapter;

import org.joda.time.DateTime;
/**
 * Created by burak on 07.11.2015.
 */
public class Submission {
    private int submissionId;
    private Student submitter;
    private DateTime submissionDate;
    private String file;
    private Task task;
    private int grade;
    private boolean evaluated;



    public Submission(int submissionId, Student submitter, DateTime submissionDate, Task task) {
        this.submissionId = submissionId;
        this.submissionDate = submissionDate;
        this.submitter = submitter;
        this.task = task;
        this.evaluated = false;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public Student getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Student submitter) {
        this.submitter = submitter;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }
}
