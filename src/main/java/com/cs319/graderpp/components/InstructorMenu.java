package com.cs319.graderpp.components;

import com.cs319.graderpp.models.Instructor;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Created by burak on 12.11.2015.
 */
public class InstructorMenu extends DefaultMenuModel {

    public InstructorMenu(Instructor instructor) {
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("System Management");
        DefaultMenuItem item = new DefaultMenuItem("Task Manager");
        item.setUrl("task-manager.xhtml");
        item.setIcon("ui-icon-document");
        firstSubmenu.addElement(item);

        item = new DefaultMenuItem("Course Manager");
        item.setIcon("ui-icon-document");
        item.setUrl("course-manager.xhtml");
        firstSubmenu.addElement(item);

        //Second submenu
        DefaultSubMenu profile = new DefaultSubMenu("Profile");

        item = new DefaultMenuItem("Logout");
        item.setCommand("#{loginMB.logout()}");
        profile.addElement(item);


        addElement(firstSubmenu);
        addElement(profile);
    }
}
