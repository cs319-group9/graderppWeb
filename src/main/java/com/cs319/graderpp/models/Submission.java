package com.cs319.graderpp.models;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Submission {
    private String submissionId;
    private Student submitter;
    private DateTime submissionDate;
    private Task task;
    private CodeFile codeFile;
    private boolean evaluated;
    private int grade;
    private String evaluationResultNote;

    public Submission(Student submitter, DateTime submissionDate) {
        this.submissionDate = submissionDate;
        this.submitter = submitter;
        this.evaluated = false;

        this.task = null;
        this.codeFile = null;
    }

    public String filesToString() {
        if( codeFile != null) {
            String tmp = "name: " + codeFile.getFileName() + ", path: " + codeFile.getFullPath() + ", size: "
                    + codeFile.getSize() + ";\n";
            return tmp;
        }

        return null;
    }

    public CodeFile getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(CodeFile codeFile) {
        this.codeFile = codeFile;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
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

    public String getEvaluationResultNote() {
        return evaluationResultNote;
    }

    public void setEvaluationResultNote(String evaluationResultNote) {
        this.evaluationResultNote = evaluationResultNote;
    }
}
