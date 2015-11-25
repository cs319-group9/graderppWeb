package com.cs319.graderpp.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class HomepageMB extends PageControllerMB {

    private String welcomeMessage;

    @Override
    public void loadComponents()
    {
        loadMenu(getLoginMB().getSignedUser());

    }

    @Override
    public void loadData()
    {
        this.welcomeMessage = "Welcome to Grader++ !";
    }


    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

}

