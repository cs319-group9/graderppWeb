package com.cs319.graderpp.adapter;

import org.joda.time.DateTime;
/**
 * Created by burak on 07.11.2015.
 */
public class SubmissionInfo {
    private int submissionId;
    private DateTime submissionDate;
    private String file;
    private String taskName;
    private int grade;
    private boolean evaluated;


    public SubmissionInfo(int submissionId, DateTime submissionDate, String taskName) {
        this.submissionId = submissionId;
        this.submissionDate = submissionDate;
        this.taskName = taskName;

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }
}
