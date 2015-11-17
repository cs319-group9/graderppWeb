package com.cs319.graderpp.mbeans;

import com.cs319.graderpp.adapter.Assistant;
import com.cs319.graderpp.adapter.Course;
import com.cs319.graderpp.adapter.Instructor;
import com.cs319.graderpp.adapter.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private int tabIndex;

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
    }

    @Override
    public void loadComponents()
    {

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

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
}
