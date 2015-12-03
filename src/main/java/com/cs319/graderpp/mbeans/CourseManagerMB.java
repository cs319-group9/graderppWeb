package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.models.Assistant;
import com.cs319.graderpp.models.Course;
import com.cs319.graderpp.models.Instructor;
import com.cs319.graderpp.models.Task;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 17.11.2015.
 */

@ViewScoped
@ManagedBean
public class CourseManagerMB extends PageControllerMB {

    private List<Course> courses;
    private Course selectedCourse;
    private Course tempCourse;

    @Override
    public void loadData() {
        if (getLoginMB().getSignedUser() instanceof Instructor) {
            //courses = getDataService().getRealDataService().getCourseList();
        }

        tempCourse = new Course();
    }

    @Override
    public void loadComponents() {
        loadMenu(getLoginMB().getSignedUser());
    }


    @Override
    public boolean isAuthorized() {
        if (getLoginMB().getSignedUser() instanceof Instructor) {
            return true;
        }
        return false;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public Course getTempCourse() {
        return tempCourse;
    }

    public void setTempCourse(Course tempCourse) {
        this.tempCourse = tempCourse;
    }

    public void addCourse() {
        if (tempCourse != null) {
            //tempCourse.setCourseId((int) (Math.random() * 1000));

            getDataService().getRealDataService().addCourse(tempCourse);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Course added: " + tempCourse.getCourseName() + " "
                            + tempCourse.getCourseCode() + ", ID:" + tempCourse.getCourseId()));

            //UPDATE THE TASK LIST
            //courses = getDataService().getRealDataService().getCourseList();

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task cannot be added!"));
        }
    }

    public void editCourse() {

    }

    /*public String onFlowProcess(FlowEvent event) {
        if(event.getNewStep().equals("") || event.getNewStep() == null) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }*/
}
