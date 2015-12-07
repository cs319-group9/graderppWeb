package com.cs319.graderpp.service;

import com.cs319.graderpp.models.*;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public interface DataServiceImpl {

    String addSubmission(Submission submission);
    void updateTask(String taskId, Task newTask);
    void addTask(Task task);
    void addCourse(Course course);
    User getSignedUser(String username, String password);

    List<Assistant> findAllAssistants();
    List<Course> findAllCourses();
    List<Student> findAllStudents();
    Task findTaskById(String taskId);
    Course findCourseById(String courseId);
    User findUserById(String userId);
    Submission findSubmissionById(String submissionId);

    List<Task> findAllTasksOfUser(User user);
    List<Submission> findSubmissionsOfUser(User user);
    List<Course> findCoursesOfUser(User user);
    List<Task> findTasksOfCourse(String courseId);
    List<Submission> findSubmissionsOfTask(String taskId);
    Task findTaskOfSubmission(String submissionId);



    //public List<User> getUserList();
    //public void setUserList(List<User> userList);
    //public List<Task> getTaskList();
    //void setTaskList(List<Task> taskList);
    //List<Course> getCourseList();
    //public void setCourseList(List<Course> courseList);
    //List<Submission> getSubmissionList();
    //public void setSubmissionList(List<Submission> submissionList);

}
