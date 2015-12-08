package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.misc.LazyLoading;
import com.cs319.graderpp.misc.SubmitFiles;
import com.cs319.graderpp.models.*;
import org.apache.poi.util.IOUtils;
import org.joda.time.DateTime;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class SubmissionMB extends PageControllerMB {

    private List<Task> availableTasks;
    private Task selectedTask;
    private UploadedFile file;

    @ManagedProperty("#{lazyLoading}")
    private LazyLoading lazyLoading;

    @ManagedProperty("#{submitFiles}")
    private SubmitFiles submitFiles;

    @Override
    public void loadData() {

        //we are assigning the relevant task list but removing the ones which is submission made on it before
        List<Task> tasks = lazyLoading.getTasksOfUser(getLoginMB().getSignedUser());
        availableTasks = new ArrayList<Task>(tasks);

        List<Submission> submissions = lazyLoading.getSubmissionsOfStudent((Student) getLoginMB().getSignedUser());
        List<Task> tasksToRemove = new ArrayList<Task>();

        for(Submission submission : submissions)
        {
            Task taskOfSubmission = lazyLoading.getTaskOfSubmission(submission);
            for(Task t : tasks)
            {
                //if this task is among the submitted tasks delete it
                if(t.getTaskId().equals(taskOfSubmission.getTaskId()))
                {
                    tasksToRemove.add(t);
                }
            }

        }

        for(Task t : tasksToRemove)
        {
            availableTasks.remove(t);
        }

        // tasks = getDataService().getRealDataService().findAllTasksOfUser((Student) getLoginMB().getSignedUser());
    }

    @Override
    public void loadComponents() {
        loadMenu(getLoginMB().getSignedUser());
    }

    @Override
    public boolean isAuthorized()
    {
        if( getLoginMB().getSignedUser() instanceof Student )
            return true;
        return false;
    }

    public void taskChangeListener(ValueChangeEvent event)//AjaxBehaviorEvent event)
    {
        System.out.println("on selectTask event");
        selectedTask = (Task) event.getNewValue();
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        this.file = event.getFile();

        // if task id is not set dont send
        if (selectedTask == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission not made, please select a Task!"));
        } else {

            Submission submission = new Submission((Student) getLoginMB().getSignedUser());
            submission.setSubmissionDate(DateTime.now().toDate());
            submission.setTask(selectedTask);
            getDataService().getRealDataService().addSubmission(submission);


            //code below copies the uploaded file to the system
            String path = "/home/burak/uploadDeneme";//FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(
                    new File(path, //FacesContext.getCurrentInstance().getExternalContext().getRealPath("//WEB-INF//files//"),
                            file.getFileName()));

            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }

            submitFiles.submit(path + "/" + file.getFileName());

            //instantiate CodeFile object for each file and add to the submission
            CodeFile codeFile = new CodeFile(file.getFileName(), path + file.getFileName(), file.getSize());
            codeFile.setInputStream(input);

            submission.setCodeFile(codeFile);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Submission Made to the Task: " + selectedTask.getTaskId()));
        }
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<Task> getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(List<Task> availableTasks) {
        this.availableTasks = availableTasks;
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public LazyLoading getLazyLoading() {
        return lazyLoading;
    }

    public void setLazyLoading(LazyLoading lazyLoading) {
        this.lazyLoading = lazyLoading;
    }

    public SubmitFiles getSubmitFiles() {
        return submitFiles;
    }

    public void setSubmitFiles(SubmitFiles submitFiles) {
        this.submitFiles = submitFiles;
    }
}

