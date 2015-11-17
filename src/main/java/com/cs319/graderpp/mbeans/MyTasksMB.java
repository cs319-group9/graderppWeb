package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.adapter.Course;
import com.cs319.graderpp.adapter.Student;
import com.cs319.graderpp.adapter.Submission;
import com.cs319.graderpp.adapter.Task;
import com.cs319.graderpp.components.StudentMenu;
import com.cs319.graderpp.misc.Redirection;
import com.cs319.graderpp.service.GraderppService;
import org.primefaces.mobile.component.page.Page;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class MyTasksMB extends PageControllerMB {

    private List<Task> tasks;

    @Override
    public void loadData()
    {
        tasks = ((Student) getLoginMB().getSignedUser()).getAssignedTasks();
    }

    @Override
    public void loadComponents()
    {
        loadMenu( getLoginMB().getSignedUser() );
    }

    public Submission getSubmission(Task task)
    {
        return task.getSubmissionFrom((Student) getLoginMB().getSignedUser());
    }

    @Override
    public boolean isAuthorized()
    {
        if( getLoginMB().getSignedUser() instanceof Student)
        {
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
}

