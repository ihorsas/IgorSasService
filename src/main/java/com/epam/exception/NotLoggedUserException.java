package com.epam.exception;

public class NotLoggedUserException extends Exception {

    public NotLoggedUserException() {
        super();
    }

    public NotLoggedUserException(String s) {
        super(s);
    }
}
