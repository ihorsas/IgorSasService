package com.epam.dao;

import com.epam.exception.UserNotLoggedInException;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.utills.CSVUserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserDAO {
    private static Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private final File file;
    private User user;

    public UserDAO() {
        file = new File("src/main/resources/users.csv");
    }

    public boolean logIn(String username, String password) {
        AtomicBoolean cond = new AtomicBoolean(false);
        CSVUserManager.readUsers(file)
                .stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password))
                .forEach(user -> {
                    this.user = user;
                    cond.set(true);
                });
        return cond.get();
    }

    public boolean registerNewUser(User user) {
        LOGGER.info("registering new User");
        List<User> userList = CSVUserManager.readUsers(file);
        if (!userList.contains(user)) {
            userList.add(user);
            CSVUserManager.writeUsers(userList, file);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return CSVUserManager.readUsers(file);
    }

    public boolean deleteUser(User user) {
        LOGGER.info("deleting user");
        List<User> userList = CSVUserManager.readUsers(file);
        if(userList.remove(user)){
            CSVUserManager.writeUsers(userList, file);
            return true;
        }
        return false;
    }

    public List<Role> getRolesForCurrentUser() throws UserNotLoggedInException {
        if (Objects.isNull(user)) {
            throw new UserNotLoggedInException();
        } else {
            return user.getRoles();
        }
    }
}
