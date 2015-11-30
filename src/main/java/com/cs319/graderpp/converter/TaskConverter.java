package com.cs319.graderpp.converter;

import com.cs319.graderpp.models.Task;
import com.cs319.graderpp.service.DataService;

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

    @ManagedProperty("#{dataService}")
    private DataService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Task tmp = service.getRealDataService().findTaskById(Integer.parseInt(value));
        return tmp;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null)
            return "";

        Task tmp = (Task) value;
        return tmp.getTaskId() + "";
    }

    public DataService getService() {
        return service;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}

