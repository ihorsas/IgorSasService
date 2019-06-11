package com.epam.bo;

import com.epam.exception.NotLoggedUserException;
import com.epam.dao.UserDAO;
import com.epam.model.Role;
import com.epam.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserBO {
    private UserDAO userDAO;

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
        return userList
                .stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
    }

    public List<Role> getRolesForCurrentUser() throws NotLoggedUserException {
        return userDAO.getRolesForCurrentUser();
    }
}
