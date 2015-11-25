package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.models.CodeFile;
import com.cs319.graderpp.models.Student;
import com.cs319.graderpp.models.Submission;
import com.cs319.graderpp.models.Task;
import org.apache.poi.util.IOUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 23.07.2015.
 */

@ManagedBean
@ViewScoped
public class SubmissionMB extends PageControllerMB {

    private List<Task> tasks;
    private Task selectedTask;
    private UploadedFile file;

    @Override
    public void loadData()
    {
        tasks = getService().findAllTasksOfUser((Student) getLoginMB().getSignedUser());
    }

    @Override
    public void loadComponents()
    {
        loadMenu( getLoginMB().getSignedUser() );
    }

    public void submit() throws IOException
    {
        // if task id is not set dont send
        if(selectedTask == null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission not made"));
        }
        else
        {
            int randId = (int)(Math.random() * 1000);

            Submission submission = new Submission( (Student) getLoginMB().getSignedUser(), DateTime.now(), selectedTask);
            submission.setSubmissionId(randId);

            //code below copies the uploaded file to the system
            String path = "/home/burak";//FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(new File(path, file.getFileName()));

            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }

            CodeFile codeFile = new CodeFile(file.getFileName(), path + file.getFileName(), file.getInputstream(), file.getSize() );
            submission.getCodeFiles().add( codeFile);

            getService().addSubmission(submission);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission Made to the Task: " + selectedTask.getTaskId()));
        }
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}

