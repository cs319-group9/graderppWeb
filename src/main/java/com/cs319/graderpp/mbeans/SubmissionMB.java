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
public class SubmissionMB {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private MenuModel menu;
    private List<SelectItem> tasks;
    private int selectedTaskId = -1;

    @PostConstruct
    public void init() {
        if( !loginMB.isSignedIn() ) {
            Redirection.toLoginPage();
        } else {
            loadComponents();

            tasks = new ArrayList<SelectItem>();
            for( Task task : service.getTaskList() ) {
                tasks.add(new SelectItem( task.getTaskId() , task.getCourseCode() + " - " + task.getTaskName() ) );
            }

        }

    }

    public void loadComponents()
    {
        menu = new StudentMenu((Student) loginMB.getSignedUser() );
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
            Task tmpTask = service.findTaskById(selectedTaskId);
            Submission submission = new Submission( randId, (Student) loginMB.getSignedUser(), DateTime.now(), tmpTask);

            service.addSubmission(submission);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission Made to the Task: " + selectedTaskId));
        }
    }

    public int getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(int selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public List<SelectItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<SelectItem> tasks) {
        this.tasks = tasks;
    }

    public GraderppService getService() {
        return service;
    }

    public void setService(GraderppService service) {
        this.service = service;
    }

    /*    public String goToStudentInfo(){
        return "studentInfo.xhtml";
    }
    /*
    public void goToMainPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }*/



}

