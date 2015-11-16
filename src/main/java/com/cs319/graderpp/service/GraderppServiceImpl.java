package com.cs319.graderpp.service;

import com.cs319.graderpp.adapter.*;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service(value = "graderppService")
public class GraderppServiceImpl implements GraderppService {

    private List<User> userList;
    private List<Task> taskList;

    @PostConstruct
    public void init()
    {
        taskList = prepareTaskList();
        userList = prepareUserList();
    }

    private List<Task> prepareTaskList()
    {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(4, "CS224", "lab1_224", "20 dec."));
        tasks.add(new Task(2, "CS202", "hw1_224", "19 dec."));
        tasks.add(new Task(10, "CS102", "hw2_224", "23 dec."));
        return tasks;
    }

    private List<User> prepareUserList()
    {
        List<User> users = new ArrayList<User>();
        Student u1 = new Student("student", "password", 123);
        u1.setTasks(taskList);

        User u2 = new Instructor("instructor", "password");

        users.add(u1);
        users.add(u2);
        return users;
    }

    public void addSubmission(Submission submission)
    {
        submission.getTask().getSubmissions().add(submission);
    }

    public Task findTaskById (int taskId)
    {
        for(Task task: taskList)
        {
            if(task.getTaskId() == taskId)
                return task;
        }
        return null;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}