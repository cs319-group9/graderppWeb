package com.cs319.graderpp.models;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

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
    private List<CodeFile> codeFiles;

    public Submission(Student submitter, DateTime submissionDate, Task task) {
        this.submissionDate = submissionDate;
        this.submitter = submitter;
        this.task = task;
        this.evaluated = false;
        this.codeFiles = new ArrayList<CodeFile>();
    }

    public List<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public void setCodeFiles(List<CodeFile> codeFiles) {
        this.codeFiles = codeFiles;
    }

    public String filesToString() {
        String tmp = "";
        for (CodeFile file : codeFiles) {
            tmp += "name: " + file.getFileName() + ", path: " + file.getFullPath() + ", size: " + file.getSize() + ";\n";
        }
        return tmp;
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
