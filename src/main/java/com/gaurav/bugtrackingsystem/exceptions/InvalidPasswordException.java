package com.gaurav.bugtrackingsystem.exceptions;

public class InvalidPasswordException extends Throwable {
    public InvalidPasswordException(String password) {
        super("invalid password " + password);
    }
}
