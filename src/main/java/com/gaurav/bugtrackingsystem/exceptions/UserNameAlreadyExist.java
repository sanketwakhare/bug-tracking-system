package com.gaurav.bugtrackingsystem.exceptions;

import com.gaurav.bugtrackingsystem.models.User;

import java.util.Optional;

public class UserNameAlreadyExist extends Throwable {
    public UserNameAlreadyExist(String name) {
        super("user name already exist " + name);
    }
}
