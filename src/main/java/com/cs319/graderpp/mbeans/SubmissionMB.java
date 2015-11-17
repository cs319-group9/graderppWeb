package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.adapter.Instructor;
import com.cs319.graderpp.adapter.Student;
import com.cs319.graderpp.adapter.Submission;
import com.cs319.graderpp.adapter.Task;
import com.cs319.graderpp.components.InstructorMenu;
import com.cs319.graderpp.components.StudentMenu;
import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.Redirection;
import com.cs319.graderpp.service.GraderppService;
import org.joda.time.DateTime;
import org.primefaces.context.ApplicationContext;
import org.primefaces.model.menu.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class SubmissionMB extends PageControllerMB {

    private List<SelectItem> tasks;
    private int selectedTaskId = -1;

    @Override
    public void loadData()
    {
        tasks = new ArrayList<SelectItem>();
        for( Task task : getService().getTaskList() ) {
            tasks.add(new SelectItem( task.getTaskId() , task.getCourse().getCourseCode() + " - " + task.getTaskName() ) );
        }
    }

    @Override
    public void loadComponents()
    {
        setMenu(new StudentMenu((Student) getLoginMB().getSignedUser() ));
    }

    public void submit()
    {
        // if task id is not set dont send
        if(selectedTaskId == -1)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission not made"));
        }
        else
        {
            int randId = (int)(Math.random() * 1000);
            Task tmpTask = getService().findTaskById(selectedTaskId);
            Submission submission = new Submission( randId, (Student) getLoginMB().getSignedUser(), DateTime.now(), tmpTask);

            getService().addSubmission(submission);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission Made to the Task: " + selectedTaskId));
        }
    }

    public int getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(int selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }


    public List<SelectItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<SelectItem> tasks) {
        this.tasks = tasks;
    }


}

