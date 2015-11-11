package com.cs319.graderpp.mbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.cs319.graderpp.adapter.User;
import com.cs319.graderpp.service.GraderppService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */



@ManagedBean
@SessionScoped
public class LoginManagedBean {

    @ManagedProperty("#{graderppService}")
    private GraderppService graderppService;

    private String username;
    private String password;
    private User signedUser;

    @PostConstruct
    public void init(){}

    public void login() {
        List<User> users = graderppService.getUserList();

        for( User user: users)
        {
           if(user.getUsername().equals(username) &&
                   user.getPassword().equals(password)) {
               signedUser = user;
           }
        }

        FacesContext context = FacesContext.getCurrentInstance();
        if(signedUser == null)
        {
            context.addMessage(null, new FacesMessage("No users found!"));
        }
        else
        {
            context.addMessage(null, new FacesMessage("Welcome " + signedUser.getUsername()));
        }
    }

    public boolean isSignedIn()
    {
        return (signedUser == null) ? false : true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getSignedUser() {
        return signedUser;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public GraderppService getGraderppService() {
        return graderppService;
    }

    public void setGraderppService(GraderppService graderppService) {
        this.graderppService = graderppService;
    }
}
