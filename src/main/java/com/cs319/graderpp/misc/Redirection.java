package com.cs319.graderpp.misc;

import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by burak on 12.11.2015.
 */
public final class Redirection {
    public static void toLoginPage() { redirectTo("login"); }
    public static void toHomePage() { redirectTo("index"); }
    public static void toCoursesPage(){redirectTo("courses");}

    public static void redirectTo(String viewName)
    {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(viewName + ".xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
