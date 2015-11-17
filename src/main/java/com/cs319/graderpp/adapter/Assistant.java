package com.cs319.graderpp.adapter;

import com.cs319.graderpp.misc.Constants;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Assistant extends User {

    private List<Task> tasks;

    public Assistant(String username, String password) {
        super(username, password, Constants.ASSISTANT);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

