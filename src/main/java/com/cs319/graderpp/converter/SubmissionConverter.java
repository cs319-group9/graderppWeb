package com.cs319.graderpp.converter;

import com.cs319.graderpp.models.Submission;
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
public class SubmissionConverter implements Converter {

    @ManagedProperty("#{dataService}")
    private DataService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Submission tmp = service.getRealDataService().findSubmissionById(Integer.parseInt(value));
        return tmp;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Submission tmp = (Submission) value;
        return tmp.getSubmissionId() + "";
    }

    public DataService getService() {
        return service;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}

