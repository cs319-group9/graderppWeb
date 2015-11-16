package com.cs319.graderpp.service;

import com.cs319.graderpp.adapter.Submission;
import com.cs319.graderpp.adapter.Task;
import com.cs319.graderpp.adapter.User;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public interface GraderppService {
    public List<User> getUserList();
    public void setUserList(List<User> userList);
    public List<Task> getTaskList();
    public void setTaskList(List<Task> taskList);
    public void addSubmission(Submission submission);
    public Task findTaskById (int taskId);
}
