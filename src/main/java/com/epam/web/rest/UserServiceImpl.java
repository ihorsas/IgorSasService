package com.epam.web.rest;

import com.epam.bo.UserBO;
import com.epam.dao.NotLoggedUserException;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.web.fault.FaultMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.List;

public class UserServiceImpl implements UserService {
    private Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserBO userBO;

    public UserServiceImpl() {
        userBO = new UserBO();
    }

    @Override
    public Response getAllUsers() {
        logger.info("getting all users in service");
        return Response.ok().entity(userBO.getAllUsers()).build();
    }

    @Override
    public Response getRoles(String name) {
        logger.info("getting all users in service");
        try {
            return Response.ok().entity(userBO.getRolesForCurrentUser()).build();
        } catch (NotLoggedUserException e) {
            logger.error(FaultMessage.USER_HAS_NO_ACCESS);
            return Response.status(Response.Status.FORBIDDEN).entity(FaultMessage.USER_HAS_NO_ACCESS).build();
        }
    }

    @Override
    public Response getUserByRole(Role role) {
        logger.info("getting users by role");
        List<User> userList = userBO.getUsersByRole(role);
        if(userList.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(FaultMessage.USER_NOT_EXIST).build();
        }
        return Response.ok().entity(userList).build();
    }

    @Override
    public Response addUser(User user) {
        logger.info("adding new user in system");
        if(userBO.registerNewUser(user)){
            return Response.ok().build();
        } else {
            logger.error(FaultMessage.SUCH_USER_ALREADY_EXIST);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(FaultMessage.SUCH_USER_ALREADY_EXIST).build();
        }
    }

    @Override
    public Response removeBook(User user) {
        logger.info("removing user in service");
        if (!userBO.deleteUser(user)) {
            logger.error(FaultMessage.USER_NOT_EXIST);
            return Response.status(Response.Status.NOT_FOUND).entity(FaultMessage.USER_NOT_EXIST).build();
        } else {
            return Response.ok().build();
        }
    }
}
