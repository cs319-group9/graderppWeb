package com.cs319.graderpp.converter;

import com.cs319.graderpp.models.Course;
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
public class CourseConverter implements Converter {

    @ManagedProperty("#{dataService}")
    private DataService service;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Course tmp = service.getRealDataService().findCourseById(value);
        return tmp;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Course tmp = (Course) value;
        System.out.println("Course name " + tmp.getCourseCode() + " id " + tmp.getCourseId());
        return tmp.getCourseId() + "";
    }

    public DataService getService() {
        return service;
    }

    public void setService(DataService service) {
        this.service = service;
    }
}

