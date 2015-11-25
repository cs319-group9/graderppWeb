package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.models.Assistant;
import com.cs319.graderpp.models.Course;
import com.cs319.graderpp.models.Instructor;
import com.cs319.graderpp.models.Task;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;

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
public class TaskManagerMB extends PageControllerMB {

    private List<Task> tasks;
    private Task selectedTask;
    private TabView tabView;
    private Task tempTask;

    @Override
    public void loadData()
    {
        if ( getLoginMB().getSignedUser() instanceof Instructor)
        {
            tasks = new ArrayList<Task>();
            for(Course course: ((Instructor) getLoginMB().getSignedUser()).getCourses())
            {
                for(Task task : course.getTasks())
                {
                    tasks.add(task);
                }
            }
        }
        else if( getLoginMB().getSignedUser() instanceof Assistant)
        {
            tasks = ((Assistant) getLoginMB().getSignedUser()).getTasks();
        }

        tempTask = new Task();
    }

    @Override
    public void loadComponents()
    {
        loadMenu(getLoginMB().getSignedUser());
    }


    @Override
    public boolean isAuthorized()
    {
        if( getLoginMB().getSignedUser() instanceof Instructor
            || getLoginMB().getSignedUser() instanceof Assistant)
        {
            return true;
        }
        return false;
    }

    public boolean isInstructor()
    {
        if( getLoginMB().getSignedUser() instanceof Instructor)
        {
            return true;
        }
        return false;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public TabView getTabView() {
        return tabView;
    }

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }

    public void changeTabAndSetTask(int index, Task task)
    {
        setSelectedTask(task);
        tabView.setActiveIndex(index);
    }

    public void selectTask(Task task)
    {
        setSelectedTask(task);
        RequestContext.getCurrentInstance().update(":addTabPanels");
        RequestContext.getCurrentInstance().update(":editTabPanel");
        RequestContext.getCurrentInstance().update(":submissionTabPanel");

        Tab t1 = tabView.findTab(":addTab");
        Tab t2 = tabView.findTab(":editTab");
        System.out.println(t1 + " " + t2);

        for(Tab tab : tabView.getLoadedTabs())
        {
            System.out.println(tab.getAriaLabel() + tab.getClientId() + tab.getId());
        }

        System.out.println(selectedTask.getTaskName());

        /*Tab submissionTab = tabView.getLoadedTabs();
        setSelectedTask(task);
        submissionTab.setLoaded(true);
        submissionTab.setDisabled(false);
        submissionTab.setTitle("new title");
        */
    }

    public void addTask() {
        if (tempTask != null) {
            tempTask.setTaskId( (int)(Math.random() * 1000) );
            getService().getTaskList().add(tempTask);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Task added: " + tempTask.getTaskName() + ", ID:" + tempTask.getTaskId()));

            //UPDATE THE TASK LIST
            tasks = getService().getTaskList();

            //update the course
            tempTask.getCourse().getTasks().add(tempTask);

        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task cannot be added!"));
        }
    }


    public Task getTempTask() {
        return tempTask;
    }

    public void setTempTask(Task tempTask) {
        this.tempTask = tempTask;
    }
}
