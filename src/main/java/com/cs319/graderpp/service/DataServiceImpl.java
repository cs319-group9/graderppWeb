package com.cs319.graderpp.service;

import com.cs319.graderpp.models.*;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public interface DataServiceImpl {
    public List<User> getUserList();

    public void setUserList(List<User> userList);

    public List<Task> getTaskList();

    public void setTaskList(List<Task> taskList);

    public void addSubmission(Submission submission);

    public Task findTaskById(int taskId);

    public Course findCourseById(int courseId);

    public User findUserById(int userId);

    public Submission findSubmissionById(int submissionId);

    public List<Course> getCourseList();

    public void setCourseList(List<Course> courseList);

    public List<Submission> getSubmissionList();

    public void setSubmissionList(List<Submission> submissionList);

    public List<Assistant> findAllAssistants();

    public void updateTask(int taskId, Task newTask);

    public List<Task> findAllTasksOfUser(User user);

    public void addTask(Task task);

    public void addCourse(Course course);
}
