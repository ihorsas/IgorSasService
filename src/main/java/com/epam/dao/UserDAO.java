package com.epam.dao;

import com.epam.exception.NotLoggedUserException;
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
    private final File file;
    private User user;
    private static Logger LOGGER = LogManager.getLogger(UserDAO.class);

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
            CSVUserManager.writeBooks(userList, file);
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
        ListIterator<User> it = userList.listIterator();

        if (isUserInSystem(user)) {
            while (it.hasNext()) {
                User user1 = it.next();
                if (user1.equals(user)) {
                    it.remove();
                    CSVUserManager.writeBooks(userList, file);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Role> getRolesForCurrentUser() throws NotLoggedUserException {
        if (Objects.isNull(user)) {
            throw new NotLoggedUserException();
        } else {
            return user.getRoles();
        }
    }

    private boolean isUserInSystem(User user) {
        LOGGER.info("checking user is in system");
        List<User> userList = CSVUserManager.readUsers(file);
        for (User user1 : userList) {
            if (user1.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
