package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.misc.LazyLoading;
import com.cs319.graderpp.models.Student;
import com.cs319.graderpp.models.Submission;
import com.cs319.graderpp.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class MyTasksMB extends PageControllerMB {

    private List<Task> tasks;

    @ManagedProperty("#{lazyLoading}")
    LazyLoading lazyLoading;

    @Override
    public void loadData() {
        tasks = lazyLoading.getTasksOfUser( getLoginMB().getSignedUser() );
        for(Task task : tasks)
        {
            lazyLoading.getSubmissionsOfTask(task);
        }
    }

    @Override
    public void loadComponents() {
        loadMenu(getLoginMB().getSignedUser());
    }

    public Submission getSubmission(Task task) {
        if(task.getSubmissions() == null)
        {
            lazyLoading.getSubmissionsOfTask(task);
        }

        return task.getSubmissionFrom((Student) getLoginMB().getSignedUser());
    }

    @Override
    public boolean isAuthorized() {
        if (getLoginMB().getSignedUser() instanceof Student) {
            return true;
        }
        return false;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public LazyLoading getLazyLoading() {
        return lazyLoading;
    }

    public void setLazyLoading(LazyLoading lazyLoading) {
        this.lazyLoading = lazyLoading;
    }


}

