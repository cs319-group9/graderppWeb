package com.cs319.graderpp.mbeans;

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
public class HomepageManagedBean {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    @ManagedProperty("#{loginManagedBean}")
    private LoginManagedBean loginMB;

    private String welcomeMessage;
    private MenuModel menu;

    @PostConstruct
    public void init() {
        this.welcomeMessage = "Welcome to the Student Registration System! Please select an action";
        if(loginMB.isSignedIn() ) {
            this.welcomeMessage = "Logged in as an ";
            loadMenu();
        }
        else if(loginMB.isSignedIn()  ) {
            this.welcomeMessage = "Logged in as a student. \nUsername: " + loginMB.getUsername()
                    + "\nLogin time: " ;
            loadMenu();
        }
        else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void loadMenu() {
        if(true)
        {
            menu = new DefaultMenuModel();

            //First submenu
            DefaultSubMenu firstSubmenu = new DefaultSubMenu("Student Management");
            DefaultMenuItem item = new DefaultMenuItem("Add Student");
            item.setCommand("#{homepageManagedBean.goToAddStudent()}");
            item.setIcon("ui-icon-plus");
            firstSubmenu.addElement(item);

            DefaultMenuItem item2 = new DefaultMenuItem("Add Course");
            item2.setCommand("#{homepageManagedBean.goToAddCourse()}");
            item2.setIcon("ui-icon-plus");
            firstSubmenu.addElement(item2);

            item = new DefaultMenuItem("List Courses");
            item.setCommand("#{homepageManagedBean.goToCourseList()}");
            item.setIcon("ui-icon-document");
            firstSubmenu.addElement(item);

            item = new DefaultMenuItem("List Students");
            item.setCommand("#{homepageManagedBean.goToListStudents()}");
            item.setIcon("ui-icon-clipboard");
            firstSubmenu.addElement(item);

            item = new DefaultMenuItem("Student Info");
            item.setCommand("#{homepageManagedBean.goToStudentInfo()}");
            item.setIcon("ui-icon-key");
            firstSubmenu.addElement(item);

            menu.addElement(firstSubmenu);

            //Second submenu
            DefaultSubMenu secondSubmenu = new DefaultSubMenu("Account");
            item = new DefaultMenuItem("Account Info");
            item.setIcon("ui-icon-key");
            //item.setDisabled(true);
            item.setCommand("#{loginManagedBean.goToAccountInfo()}");
            secondSubmenu.addElement(item);

            item = new DefaultMenuItem("Logout");
            item.setIcon("ui-icon-close");
            item.setCommand("#{loginManagedBean.logout()}");
            secondSubmenu.addElement(item);

            menu.addElement(secondSubmenu);
        }
        else
        {
            menu = new DefaultMenuModel();

            //First submenu
            DefaultSubMenu firstSubmenu = new DefaultSubMenu("Account");

            DefaultMenuItem item = new DefaultMenuItem("Select Courses");
            item.setCommand("#{homepageManagedBean.goToCourseSelect()}");
            item.setIcon("ui-icon-suitcase");
            firstSubmenu.addElement(item);

            item = new DefaultMenuItem("Schedule");
            //item.setDisabled(true);
            item.setCommand("#{homepageManagedBean.goToSchedule()}");
            item.setIcon("ui-icon-calendar");
            firstSubmenu.addElement(item);

            //Second submenu
            DefaultSubMenu secondSubmenu = new DefaultSubMenu("Account");

            item = new DefaultMenuItem("Account Info");
            item.setIcon("ui-icon-key");
            //item.setDisabled(true);
            item.setCommand("#{loginManagedBean.goToAccountInfo()}");
            secondSubmenu.addElement(item);

            item = new DefaultMenuItem("Logout");
            item.setIcon("ui-icon-close");
            item.setCommand("#{loginManagedBean.logout()}");
            secondSubmenu.addElement(item);

            menu.addElement(firstSubmenu);
            menu.addElement(secondSubmenu);
        }
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public LoginManagedBean getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginManagedBean loginMB) {
        this.loginMB = loginMB;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }


/*    public String goToStudentInfo(){
        return "studentInfo.xhtml";
    }
    /*
    public void goToMainPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }*/



}

