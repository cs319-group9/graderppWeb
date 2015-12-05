package com.cs319.graderpp.models;

import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.LazyLoading;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */
public class Assistant extends User {

    private List<Task> tasks;

    public Assistant(String username, String password, String fullName) {
        super(username, password, fullName);
        tasks = null;
    }

    public List<Task> getTasks() {
            return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

