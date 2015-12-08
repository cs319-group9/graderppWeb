package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.LazyLoading;
import org.joda.time.DateTime;

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

    private int numTestCases;
    private int memorySize;
    private int stackSize;
    private int numProcesses; //-1 means unlimited
    private double timeLimitInSeconds;

    private Course course;
    private Assistant assistant;

    private List<Submission> submissions;
    private List<MakeFile> makeFiles;
    private List<TestCaseFile> inputTestFiles;
    private List<TestCaseFile> outputTestFiles;

    public Task() {}

    public Task(String taskName, Date dueDate) {
        this.submissions = null;
        this.inputTestFiles = null;
        this.outputTestFiles = null;
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

    public String getReadableDueDate() {
        if(dueDate != null)
        {
            DateTime dt = new DateTime(this.dueDate);
            String str = dt.toString("dd-MM-yyyy");
            return str;
        }
        else
        {
            return "Date not set";
        }
    }

    public List<MakeFile> getMakeFiles() {
        return makeFiles;
    }

    public void setMakeFiles(List<MakeFile> makeFiles) {
        this.makeFiles = makeFiles;
    }

    public List<TestCaseFile> getInputTestFiles() {
        return inputTestFiles;
    }

    public void setInputTestFiles(List<TestCaseFile> inputTestFiles) {
        this.inputTestFiles = inputTestFiles;
    }

    public List<TestCaseFile> getOutputTestFiles() {
        return outputTestFiles;
    }

    public void setOutputTestFiles(List<TestCaseFile> outputTestFiles) {
        this.outputTestFiles = outputTestFiles;
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

    public int getNumTestCases() {
        return numTestCases;
    }

    public void setNumTestCases(int numTestCases) {
        this.numTestCases = numTestCases;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public int getNumProcesses() {
        return numProcesses;
    }

    public void setNumProcesses(int numProcesses) {
        this.numProcesses = numProcesses;
    }

    public double getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public void setTimeLimitInSeconds(double timeLimitInSeconds) {
        this.timeLimitInSeconds = timeLimitInSeconds;
    }
}
