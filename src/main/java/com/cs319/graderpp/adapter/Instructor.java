package com.cs319.graderpp.adapter;

import com.cs319.graderpp.misc.Constants;

/**
 * Created by burak on 07.11.2015.
 */
public class Instructor extends User {

    public Instructor(String username, String password) {
        super(username, password, Constants.INSTRUCTOR);
    }

}
