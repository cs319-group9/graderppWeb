package com.cs319.graderpp.converter;

import com.cs319.graderpp.adapter.Course;
import com.cs319.graderpp.adapter.Task;
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
public class TaskConverter implements Converter {

    @ManagedProperty("#{graderppService}")
    private GraderppService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Task tmp = service.findTaskById(Integer.parseInt(value));
        return tmp;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Task tmp = (Task) value;
        return tmp.getTaskId() + "";
    }

    public GraderppService getService() {
        return service;
    }

    public void setService(GraderppService service) {
        this.service = service;
    }
}

