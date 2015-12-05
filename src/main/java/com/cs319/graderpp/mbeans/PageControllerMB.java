package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.models.*;
import com.cs319.graderpp.components.AssistantMenu;
import com.cs319.graderpp.components.InstructorMenu;
import com.cs319.graderpp.components.StudentMenu;
import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.Redirection;
import com.cs319.graderpp.service.DataService;
import com.cs319.graderpp.service.DataServiceImpl;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public abstract class PageControllerMB {

    @ManagedProperty("#{dataService}")
    DataService dataService;

    @ManagedProperty("#{loginMB}")
    private LoginMB loginMB;

    private MenuModel menu;

    @PostConstruct
    public void init() {
        if (!loginMB.isSignedIn()) {
            Redirection.toLoginPage();
        } else if (!isAuthorized()) {
            loadMenu(loginMB.getSignedUser());
        } else {
            loadComponents();
            loadData();
        }
    }

    public abstract void loadData();
    public abstract void loadComponents();
    public abstract boolean isAuthorized();

    public MenuModel getMenu() {
        return menu;
    }

    public void loadMenu(User user) {
        if (user == null) {
            return;
        }

        if (user instanceof Student) {
            setMenu(new StudentMenu((Student) user));
        } else if (user instanceof Instructor) {
            setMenu(new InstructorMenu((Instructor) user));
        } else if (user instanceof Assistant) {
            setMenu(new AssistantMenu((Assistant) user));
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


    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}

