package com.epam.web.fault;


public enum FaultMessage {
    USER_NOT_LOGGED_IN("User with name [%s] isn't logged in"),
    USER_HAS_NO_ACCESS("User with name [%s] doesn't have access to do action [%s]"),
    SUCH_USER_ALREADY_EXIST("[%s] user already exist"),
    USER_NOT_EXIST("[%s] user already exist");

    private String messageExpression;

    FaultMessage(String message) {
        this.messageExpression = message;
    }

    public String getMessageExpression() {
        return this.messageExpression;
    }
}
