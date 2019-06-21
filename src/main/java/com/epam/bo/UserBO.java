package com.epam.bo;

import com.epam.dao.UserDAO;
import com.epam.exception.UserNotLoggedInException;
import com.epam.model.Role;
import com.epam.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserBO {
    private UserDAO userDAO;
    private static final Logger LOGGER = LogManager.getLogger(UserBO.class);

    public UserBO() {
        userDAO = new UserDAO();
    }

    public boolean logIn(String username, String password) {
        return userDAO.logIn(username, password);
    }

    public boolean registerNewUser(User user) {
        return userDAO.registerNewUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(User user) {
        return userDAO.deleteUser(user);
    }

    public List<User> getUsersByRole(Role role) {
        List<User> userList = userDAO.getAllUsers();
        List<User> list = new ArrayList<>();
        userList.stream()
                .filter(user -> user.getRole().equals(role))
                .forEach(user -> {
                    list.add(user);
                    LOGGER.info(user);
                });
        return list;
    }

    public List<Role> getRolesForCurrentUser() throws UserNotLoggedInException {
        return userDAO.getRolesForCurrentUser();
    }
}
