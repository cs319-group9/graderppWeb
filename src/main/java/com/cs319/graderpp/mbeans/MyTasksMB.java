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
public class MyTasksMB {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private MenuModel menu;
    private List<Task> tasks;

    @PostConstruct
    public void init() {
        if( !loginMB.isSignedIn() ) {
            Redirection.toLoginPage();
        } else {
            loadComponents();

            tasks = ((Student) loginMB.getSignedUser()).getTasks();
        }

    }

    public void loadComponents()
    {
        menu = new StudentMenu((Student) loginMB.getSignedUser() );
    }

    public Submission getSubmission(Task task)
    {
        return task.getSubmissionFrom((Student) loginMB.getSignedUser());
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
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

