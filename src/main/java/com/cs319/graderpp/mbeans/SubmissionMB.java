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

    private List<SelectItem> tasks;
    private int selectedTaskId = -1;
    private UploadedFile file;

    @Override
    public void loadData()
    {
        tasks = new ArrayList<SelectItem>();
        for( Task task : getService().getTaskList() ) {
            tasks.add(new SelectItem( task.getTaskId() , task.getCourse().getCourseCode() + " - " + task.getTaskName() ) );
        }
    }

    @Override
    public void loadComponents()
    {
        loadMenu( getLoginMB().getSignedUser() );
    }

    public void submit() throws IOException
    {
        // if task id is not set dont send
        if(selectedTaskId == -1)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission not made"));
        }
        else
        {
            int randId = (int)(Math.random() * 1000);
            Task tmpTask = getService().findTaskById(selectedTaskId);
            Submission submission = new Submission( (Student) getLoginMB().getSignedUser(), DateTime.now(), tmpTask);
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

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission Made to the Task: " + selectedTaskId));
        }
    }

    public int getSelectedTaskId() {
        return selectedTaskId;
    }

    public void setSelectedTaskId(int selectedTaskId) {
        this.selectedTaskId = selectedTaskId;
    }


    public List<SelectItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<SelectItem> tasks) {
        this.tasks = tasks;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}

