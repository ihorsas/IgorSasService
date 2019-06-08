package com.epam.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "UserFault")
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String s) {
        super(s);
    }
}
