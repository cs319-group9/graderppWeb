package com.cs319.graderpp.models;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Submission {

    private String submissionId;
    private Student submitter;
    private Date submissionDate;

    private Task task;
    private CodeFile codeFile;

    private boolean evaluated;
    private int grade;
    private String compileLog; //(if success then it is empty string)
    private int[] gradeLogs; //List<Enum> (0=Successful, 1=Segmentation Fault, 2=Memory Exceed, 3=Time Limit exceeded, 4=wrong answer) (for each test case)
    private int[] memorySizes; //for each test case
    private double[] times; //for each test case
    private int evalStatus; //(0=waiting, 1=evaluating, 2=done)
    private boolean compileSuccess;

    public Submission(Student submitter) {
        this.submitter = submitter;
        this.evaluated = false;

        this.task = null;
        this.codeFile = null;
    }

    @Override
    public boolean equals(Object object)
    {
        if(((Submission) object).getSubmissionId().equals(this.submissionId))
            return true;
        return false;
    }

    public String filesToString() {
        if( codeFile != null) {
            String tmp = "name: " + codeFile.getFileName() + ", path: " + codeFile.getFullPath() + ", size: "
                    + codeFile.getSize() + ";\n";
            return tmp;
        }

        return null;
    }

    public String getReadableSubmissionDate() {
        if(this.submissionDate != null)
        {
            DateTime dt = new DateTime(this.submissionDate);
            String str = dt.toString("dd-MM-yyyy");
            return str;
        }
        else
        {
            return "Date not set";
        }
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

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
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

    public String getCompileLog() {
        return compileLog;
    }

    public void setCompileLog(String compileLog) {
        this.compileLog = compileLog;
    }

    public int[] getGradeLogs() {
        return gradeLogs;
    }

    public void setGradeLogs(int[] gradeLogs) {
        this.gradeLogs = gradeLogs;
    }

    public int[] getMemorySizes() {
        return memorySizes;
    }

    public void setMemorySizes(int[] memorySizes) {
        this.memorySizes = memorySizes;
    }

    public double[] getTimes() {
        return times;
    }

    public void setTimes(double[] times) {
        this.times = times;
    }

    public int getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(int evalStatus) {
        this.evalStatus = evalStatus;
    }

    public boolean isCompileSuccess() {
        return compileSuccess;
    }

    public void setCompileSuccess(boolean compileSuccess) {
        this.compileSuccess = compileSuccess;
    }
}
