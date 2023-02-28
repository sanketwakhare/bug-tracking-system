package com.gaurav.bugtrackingsystem.exceptions;

public class UserNameAlreadyExist extends Throwable {
    public UserNameAlreadyExist(String name) {
        super("user name already exist " + name);
    }
}
