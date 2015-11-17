package com.cs319.graderpp.components;

import com.cs319.graderpp.adapter.Instructor;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Created by burak on 12.11.2015.
 */
public class InstructorMenu extends DefaultMenuModel{

    public InstructorMenu(Instructor instructor) {
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Account");

        DefaultMenuItem item = new DefaultMenuItem("Select Courses");
        item.setIcon("ui-icon-suitcase");
        firstSubmenu.addElement(item);

        item = new DefaultMenuItem("Schedule");
        //item.setDisabled(true);
        item.setIcon("ui-icon-calendar");
        firstSubmenu.addElement(item);

        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Account");

        item = new DefaultMenuItem("Account Info");
        item.setIcon("ui-icon-key");
        //item.setDisabled(true);
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Logout");
        item.setIcon("ui-icon-close");
        secondSubmenu.addElement(item);

        addElement(firstSubmenu);
        addElement(secondSubmenu);
    }
}
