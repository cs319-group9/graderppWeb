package com.cs319.graderpp.components;

import com.cs319.graderpp.adapter.Student;
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
        item.setCommand("#{homepageManagedBean.goToQuickSubmission()}");
        item.setIcon("ui-icon-plus");
        firstSubmenu.addElement(item);

        item = new DefaultMenuItem("My Submissions");
        item.setCommand("#{homepageManagedBean.goToMySubmissions()}");
        item.setIcon("ui-icon-document");
        firstSubmenu.addElement(item);

        //Second submenu
        DefaultSubMenu myCourses = new DefaultSubMenu("My Courses");

        if(student.getCourses().size() > 0 ) {
            for (String course : student.getCourses()) {
                item = new DefaultMenuItem(course);
                //item.setCommand("#{homepageManagedBean.goToMySubmissions()}");
                item.setIcon("ui-icon-document");
                myCourses.addElement(item);

            }
        } else {
            item = new DefaultMenuItem("No course");
            item.setDisabled(true);
            item.setIcon("ui-icon-plus");
            myCourses.addElement(item);
        }

        addElement(firstSubmenu);
        addElement(myCourses);
    }
}
