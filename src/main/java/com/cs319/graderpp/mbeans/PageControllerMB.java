package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.adapter.*;
import com.cs319.graderpp.components.AssistantMenu;
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
public abstract class PageControllerMB {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private MenuModel menu;

    @PostConstruct
    public void init() {
        if( !loginMB.isSignedIn() ) {
            Redirection.toLoginPage();
        } else if ( !isAuthorized() ){
            loadMenu(loginMB.getSignedUser());
        }
        else {
            loadComponents();
            loadData();
        }
    }

    public void loadData()
    {
    }

    public void loadComponents()
    {}

    public boolean isAuthorized()
    {
        return true;
    }


    public MenuModel getMenu() {
        return menu;
    }

    public void loadMenu(User user)
    {
        if(user == null)
        {
            return;
        }

        switch(user.getUserType())
        {
            case Constants.STUDENT:
                setMenu( new StudentMenu((Student) user));
                break;
            case Constants.INSTRUCTOR:
                setMenu( new InstructorMenu((Instructor) user));
                break;
            case Constants.ASSISTANT:
                setMenu( new AssistantMenu((Assistant) user));
                break;
            default:
                break;
        }
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


    public GraderppService getService() {
        return service;
    }

    public void setService(GraderppService service) {
        this.service = service;
    }



}

