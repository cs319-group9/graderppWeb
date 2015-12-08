package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.misc.LazyLoading;
import com.cs319.graderpp.models.*;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 17.11.2015.
 */

@ManagedBean
@ViewScoped
public class CourseManagerMB extends PageControllerMB {

    private List<Course> availableCourses;
    private Course selectedCourse;
    private Course tempCourse;
    private Course courseToEdit;
    private List<Student> allStudents;

    @ManagedProperty("#{lazyLoading}")
    private LazyLoading lazyLoading;

    @Override
    public void loadData() {

        //LOAD ALL THE AVALIABLE TASKS FOR THE CURRENT USER
        if (getLoginMB().getSignedUser() instanceof Instructor) {
            availableCourses = new ArrayList<Course>();
            for (Course course : lazyLoading.getCoursesOfUser(getLoginMB().getSignedUser())) {
                    availableCourses.add(course);
            }
        } else {
            return;
        }

        //INITIALIZE OTHER PROPERTIES
        tempCourse = new Course();
        selectedCourse = null;
        courseToEdit = new Course();
        allStudents = getDataService().getRealDataService().findAllStudents();
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

    public void addCourse() {
        /*if (tempTask != null) {
            //tempTask.setTaskId((int) (Math.random() * 1000));

            if(tempTask.getCourse() != null
                    && tempTask.getAssistant() != null)
            {
                getDataService().getRealDataService().addTask(tempTask);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Task added: " + tempTask.getTaskName() + ", ID:" + tempTask.getTaskId()));

                System.out.println(
                        "Task name: " + tempTask.getTaskName() +
                                "id " + tempTask.getTaskId() +
                                "ta " + tempTask.getAssistant().getUsername() +
                                " ta id: " + tempTask.getAssistant().getUserId()
                );

                System.out.println( "task input files: " + inputFiles.size() );
                System.out.println( "task output files: " + outputFiles.size() );
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Task not added"));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task cannot be added!"));
        }*/
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
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

    public Course getCourseToEdit() {
        return courseToEdit;
    }

    public void setCourseToEdit(Course courseToEdit) {
        this.courseToEdit = courseToEdit;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public LazyLoading getLazyLoading() {
        return lazyLoading;
    }

    public void setLazyLoading(LazyLoading lazyLoading) {
        this.lazyLoading = lazyLoading;
    }
}
