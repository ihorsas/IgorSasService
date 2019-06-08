package com.epam.web.soap;

import com.epam.exception.ServiceException;
import com.epam.model.Role;
import com.epam.model.User;

import javax.jws.WebService;
import java.util.List;

@WebService()
public interface UserService {
    List<User> getAllUsers();

    List<Role> getRoles(String username) throws ServiceException;

    List<User> getUsersByRole(Role role) throws ServiceException;

    boolean addUser(User user) throws ServiceException;

    boolean removeBook(User user) throws ServiceException;

    boolean logIn(String username, String password);
}
