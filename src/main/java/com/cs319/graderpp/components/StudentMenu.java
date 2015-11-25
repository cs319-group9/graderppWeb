package com.cs319.graderpp.components;

import com.cs319.graderpp.models.Student;
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

        item = new DefaultMenuItem("My Tasks");
        item.setIcon("ui-icon-document");
        item.setUrl("my-tasks.xhtml");
        firstSubmenu.addElement(item);

        //Second submenu
        DefaultSubMenu profile = new DefaultSubMenu("Profile");

        /*if(student.getAssignedTasks().size() > 0 ) {
            for (Task task : student.getAssignedTasks()) {
                item = new DefaultMenuItem(task.getTaskName());
                //item.setCommand("#{homepageManagedBean.goToMySubmissions()}");
                item.setIcon("ui-icon-document");
                myTasks.addElement(item);

            }
        } else {*/

        item = new DefaultMenuItem("Logout");
        item.setCommand("#{loginMB.logout()}");
        profile.addElement(item);


        addElement(firstSubmenu);
        addElement(profile);
    }
}
