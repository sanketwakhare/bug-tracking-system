package com.gaurav.bugtrackingsystem.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("invalid username or password");
    }
}
