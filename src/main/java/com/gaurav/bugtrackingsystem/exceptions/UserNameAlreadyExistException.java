package com.gaurav.bugtrackingsystem.exceptions;

public class UserNameAlreadyExistException extends Throwable {
    public UserNameAlreadyExistException(String name) {
        super("user name already exist " + name);
    }
}
