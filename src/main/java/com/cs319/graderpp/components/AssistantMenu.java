package com.cs319.graderpp.components;

import com.cs319.graderpp.models.Assistant;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Created by burak on 12.11.2015.
 */
public class AssistantMenu extends DefaultMenuModel{

    public AssistantMenu(Assistant assistant) {
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Assignments");
        DefaultMenuItem item = new DefaultMenuItem("Task Manager");
        item.setUrl("task-manager.xhtml");
        item.setIcon("ui-icon-document");
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
