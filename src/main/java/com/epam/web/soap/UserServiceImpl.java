package com.epam.web.soap;

import com.epam.bo.UserBO;
import com.epam.exception.NotLoggedUserException;
import com.epam.exception.ServiceException;
import com.epam.model.LoginModel;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.web.fault.FaultMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.epam.web.soap.UserService")
public class UserServiceImpl implements UserService {
    private Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserBO userBO;

    public UserServiceImpl() {
        userBO = new UserBO();
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("getting all users in service");
        return userBO.getAllUsers();
    }

    @Override
    public List<Role> getRoles() throws ServiceException {
        LOGGER.info("getting all users in service");
        try {
            return userBO.getRolesForCurrentUser();
        } catch (NotLoggedUserException e) {
            String message = String.valueOf(FaultMessage.USER_HAS_NO_ACCESS);
            LOGGER.error(message);
            throw new ServiceException(message);
        }
    }

    @Override
    public List<User> getUsersByRole(String role) throws ServiceException {
        LOGGER.info("getting users by role");
        List<User> userList = userBO.getUsersByRole(new Role(role));
        if (userList.isEmpty()) {
            throw new ServiceException(String.valueOf(FaultMessage.USER_NOT_EXIST));
        }
        return userList;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        LOGGER.info("adding new user in system");
        if (userBO.registerNewUser(user)) {
            return true;
        } else {
            String message = String.valueOf(FaultMessage.SUCH_USER_ALREADY_EXIST);
            LOGGER.error(message);
            throw new ServiceException(message);
        }
    }

    @Override
    public boolean removeUser(User user) throws ServiceException {
        LOGGER.info("removing user in service");
        if (!userBO.deleteUser(user)) {
            String message = String.valueOf(FaultMessage.USER_NOT_EXIST);
            LOGGER.error(message);
            throw new ServiceException(message);
        } else {
            return true;
        }
    }

    @Override
    public boolean logIn(LoginModel loginModel) {
        return userBO.logIn(loginModel.getUsername(), loginModel.getPassword());
    }
}
