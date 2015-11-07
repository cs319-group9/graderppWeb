package com.cs319.graderpp.mbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cs319.graderpp.service.GraderppService;
/**
 * Created by burak on 07.11.2015.
 */



@ManagedBean
@ViewScoped
public class DenemeManagedBean {

    @ManagedProperty("#{graderppService}")
    private GraderppService graderppService;

    private String welcomeMessage;

    @PostConstruct
    public void init(){
        this.welcomeMessage = graderppService.getHelloMessage();
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public GraderppService getGraderppService() {
        return graderppService;
    }

    public void setGraderppService(GraderppService graderppService) {
        this.graderppService = graderppService;
    }
}
