package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.misc.LazyLoading;
import com.cs319.graderpp.models.Assistant;
import com.cs319.graderpp.models.Course;
import com.cs319.graderpp.models.Instructor;
import com.cs319.graderpp.models.Task;
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
public class TaskManagerMB extends PageControllerMB {

    private List<Task> availableTasks;
    private Task selectedTask;
    private Task tempTask;
    private Task taskToEdit;
    private List<Course> allCourses;
    private List<Assistant> allAssistants;

    private List<UploadedFile> inputFiles;
    private List<UploadedFile> outputFiles;


    @ManagedProperty("#{lazyLoading}")
    private LazyLoading lazyLoading;

    @Override
    public void loadData() {

        //LOAD ALL THE AVALIABLE TASKS FOR THE CURRENT USER
        if (getLoginMB().getSignedUser() instanceof Instructor) {
            availableTasks = new ArrayList<Task>();
            for (Course course : lazyLoading.getCoursesOfUser(getLoginMB().getSignedUser())) {
                //for (Task task : course.getTasks()) {
                for (Task task : lazyLoading.getTasksOfCourse(course)) {
                    availableTasks.add(task);
                }
            }
        } else if (getLoginMB().getSignedUser() instanceof Assistant) {
            availableTasks = lazyLoading.getTasksOfUser(getLoginMB().getSignedUser());

        }

        //INITIALIZE OTHER PROPERTIES
        tempTask = new Task();
        selectedTask = null;
        taskToEdit = new Task();
        allCourses = getDataService().getRealDataService().findAllCourses();
        allAssistants = getDataService().getRealDataService().findAllAssistants();

        inputFiles = new ArrayList<UploadedFile>();
        outputFiles = new ArrayList<UploadedFile>();
    }

    @Override
    public void loadComponents() {
        loadMenu(getLoginMB().getSignedUser());
    }


    @Override
    public boolean isAuthorized() {
        if (getLoginMB().getSignedUser() instanceof Instructor
                || getLoginMB().getSignedUser() instanceof Assistant) {
            return true;
        }
        return false;
    }

    public boolean isInstructor() {
        if (getLoginMB().getSignedUser() instanceof Instructor) {
            return true;
        }
        return false;
    }

    public void lazyLoadSelectedTask()
    {
        if(selectedTask == null)
            System.out.println("selectedTask null cannot lazyload it");
        else
        {
            int len = lazyLoading.getSubmissionsOfTask(selectedTask).size();
            System.out.println("selectedTask " + selectedTask.getTaskName() + " lazyloaded with " + len + " submissions");
        }
    }

    public List<Task> getTasks() {
        return availableTasks;
    }

    public void setTasks(List<Task> tasks) {
        this.availableTasks = availableTasks;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }


    public void addTask() {
        if (tempTask != null) {
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
        }
    }

    public void finishEditTask() {
        if (tempTask != null) {
            System.out.println( "task id: " + tempTask.getTaskId()
                                + "task name: " + tempTask.getTaskName() +
                                "task course: " + tempTask.getCourse() );

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Task added: " + tempTask.getTaskName() + ", ID:" + tempTask.getTaskId()));

        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task cannot be added!"));
        }
    }

    public void handleInputFilesUpload(FileUploadEvent event) {

        System.out.println("handleInputFilesUpload :  " + event.getFile().getFileName());
        inputFiles.add(event.getFile());
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleOutputFilesUpload(FileUploadEvent event) {

        System.out.println("handleOutputFilesUpload :  " + event.getFile().getFileName());
        outputFiles.add(event.getFile());
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Task getTempTask() {
        return tempTask;
    }

    public void setTempTask(Task tempTask) {
        this.tempTask = tempTask;
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

    public LazyLoading getLazyLoading() {
        return lazyLoading;
    }

    public void setLazyLoading(LazyLoading lazyLoading) {
        this.lazyLoading = lazyLoading;
    }

    public List<Task> getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(List<Task> availableTasks) {
        this.availableTasks = availableTasks;
    }

    public List<Assistant> getAllAssistants() {
        return allAssistants;
    }

    public void setAllAssistants(List<Assistant> allAssistants) {
        this.allAssistants = allAssistants;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public Task getTaskToEdit() {
        return taskToEdit;
    }

    public void setTaskToEdit(Task taskToEdit) {
        this.taskToEdit = taskToEdit;
    }
}
