package com.epam.exception;

public class UserNotLoggedInException extends Exception {

    public UserNotLoggedInException() {
        super();
    }

    public UserNotLoggedInException(String s) {
        super(s);
    }
}
