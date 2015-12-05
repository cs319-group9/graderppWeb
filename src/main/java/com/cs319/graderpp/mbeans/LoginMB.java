package com.cs319.graderpp.mbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.cs319.graderpp.models.User;
import com.cs319.graderpp.misc.Constants;
import com.cs319.graderpp.misc.Redirection;
import com.cs319.graderpp.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by burak on 07.11.2015.
 */

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

    @ManagedProperty("#{dataService}")
    DataService dataService;

    private String username;
    private String password;
    private User signedUser;

    @PostConstruct
    public void init() {
        if (isSignedIn()) {
            Redirection.toHomePage();
        }
    }

    public void login() {
        /*List<User> users = dataService.getRealDataService().getUserList();

        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                signedUser = user;

            }
        }*/

        signedUser = dataService.getRealDataService().getSignedUser(username, password);

        FacesContext context = FacesContext.getCurrentInstance();
        if (signedUser == null) {
            context.addMessage(null, new FacesMessage("No users found!"));
        } else {
            context.addMessage(null, new FacesMessage("Welcome " + signedUser.getUsername()));
            Redirection.toHomePage();
        }


    }

    public void logout() {
        if (isSignedIn()) {
            signedUser = null;
            Redirection.toLoginPage();
        }
    }

    public boolean isSignedIn() {
        return (signedUser != null);
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


    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }


}
