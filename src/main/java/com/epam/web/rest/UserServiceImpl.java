package com.epam.web.rest;

import com.epam.bo.UserBO;
import com.epam.exception.NotLoggedUserException;
import com.epam.model.LoginModel;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.web.fault.FaultMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.List;

public class UserServiceImpl implements UserService {
    private Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserBO userBO;

    public UserServiceImpl() {
        userBO = new UserBO();
    }

    @Override
    public Response getAllUsers() {
        LOGGER.info("getting all users in service");
        return Response.ok().entity(userBO.getAllUsers()).build();
    }

    @Override
    public Response getRoles() {
        LOGGER.info("getting roles for current user in service");
        try {
            return Response.ok().entity(userBO.getRolesForCurrentUser()).build();
        } catch (NotLoggedUserException e) {
            LOGGER.error(FaultMessage.USER_HAS_NO_ACCESS);
            return Response.status(Response.Status.FORBIDDEN).entity(FaultMessage.USER_HAS_NO_ACCESS).build();
        }
    }

    @Override
    public Response getUsersByRole(String role) {
        LOGGER.info("getting users by role: " + role);
        List<User> userList = userBO.getUsersByRole(new Role(role));
        if (userList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity(FaultMessage.USER_NOT_EXIST).build();
        }
        return Response.ok().entity(userList).build();
    }

    @Override
    public Response addUser(User user) {
        LOGGER.info("adding new user in system");
        if (userBO.registerNewUser(user)) {
            return Response.ok().build();
        } else {
            LOGGER.error(FaultMessage.SUCH_USER_ALREADY_EXIST);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(FaultMessage.SUCH_USER_ALREADY_EXIST).build();
        }
    }

    @Override
    public Response removeUser(User user) {
        LOGGER.info("removing user in service");
        if (!userBO.deleteUser(user)) {
            LOGGER.error(FaultMessage.USER_NOT_EXIST);
            return Response.status(Response.Status.NOT_FOUND).entity(FaultMessage.USER_NOT_EXIST).build();
        }
        return Response.ok().build();

    }

    @Override
    public Response logIn(LoginModel loginModel) {
        LOGGER.info("Logging in: " + loginModel);
        if (userBO.logIn(loginModel.getUsername(), loginModel.getPassword())) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(FaultMessage.USER_NOT_LOGGED_IN).build();
    }
}
