package com.epam.web.soap;

import com.epam.exception.ServiceException;
import com.epam.model.LoginModel;
import com.epam.model.Role;
import com.epam.model.User;

import javax.jws.WebService;
import java.util.List;

@WebService()
public interface UserService {
    List<User> getAllUsers();

    List<Role> getRoles() throws ServiceException;

    List<User> getUsersByRole(String role) throws ServiceException;

    boolean addUser(User user) throws ServiceException;

    boolean removeUser(User user) throws ServiceException;

    boolean logIn(LoginModel loginModel);
}
