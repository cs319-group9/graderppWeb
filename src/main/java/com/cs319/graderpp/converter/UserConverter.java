package com.cs319.graderpp.converter;

import com.cs319.graderpp.adapter.Course;
import com.cs319.graderpp.adapter.User;
import com.cs319.graderpp.service.GraderppService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by burak on 18.11.2015.
 */


@ManagedBean
@RequestScoped
public class UserConverter implements Converter {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        User tmp = service.findUserById(Integer.parseInt(value));
        return tmp;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        User tmp = (User) value;
        return tmp.getUserId() + "";
    }

    public GraderppService getService() {
        return service;
    }

    public void setService(GraderppService service) {
        this.service = service;
    }
}

