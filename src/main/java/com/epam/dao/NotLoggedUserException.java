package com.epam.dao;

public class NotLoggedUserException extends Exception {

    public NotLoggedUserException() {
        super();
    }

    public NotLoggedUserException(String s) {
        super(s);
    }
}
