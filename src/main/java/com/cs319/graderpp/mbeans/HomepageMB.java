package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.adapter.Student;
import com.cs319.graderpp.components.StudentMenu;
import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.Redirection;
import com.cs319.graderpp.service.GraderppService;
import org.primefaces.context.ApplicationContext;
import org.primefaces.model.menu.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class HomepageMB {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private String welcomeMessage;
    private MenuModel menu;

    @PostConstruct
    public void init() {
        if( !loginMB.isSignedIn() ) {
            Redirection.toLoginPage();
        } else {
            loadComponents();
        }
    }

    public void loadComponents()
    {
        this.welcomeMessage = "Welcome to Grader++ !";
        switch (loginMB.getSignedUser().getUserType())
        {
            case Constants.STUDENT:
                menu = new StudentMenu((Student) loginMB.getSignedUser() );
                break;
            case Constants.INSTRUCTOR:
                menu = new DefaultMenuModel();
                break;
            case Constants.ASSISTANT:
                menu = new DefaultMenuModel();
                break;
            default:
                menu = null;
                break;
        }
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

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
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

