package com.cs319.graderpp.components;

import com.cs319.graderpp.adapter.Student;
import com.cs319.graderpp.adapter.Task;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Created by burak on 12.11.2015.
 */
public class StudentMenu extends DefaultMenuModel {

    public StudentMenu(Student student)
    {
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Submission");
        DefaultMenuItem item = new DefaultMenuItem("Quick Submission");
        item.setUrl("submission.xhtml");
        item.setIcon("ui-icon-plus");
        firstSubmenu.addElement(item);

        item = new DefaultMenuItem("My Submissions");
        item.setIcon("ui-icon-document");
        item.setUrl("my-tasks.xhtml");
        firstSubmenu.addElement(item);

        //Second submenu
        DefaultSubMenu myTasks = new DefaultSubMenu("My Tasks");

        if(student.getTasks().size() > 0 ) {
            for (Task task : student.getTasks()) {
                item = new DefaultMenuItem(task.getTaskName());
                //item.setCommand("#{homepageManagedBean.goToMySubmissions()}");
                item.setIcon("ui-icon-document");
                myTasks.addElement(item);

            }
        } else {
            item = new DefaultMenuItem("No task");
            item.setDisabled(true);
            item.setIcon("ui-icon-plus");
            myTasks.addElement(item);
        }

        addElement(firstSubmenu);
        addElement(myTasks);
    }
}
