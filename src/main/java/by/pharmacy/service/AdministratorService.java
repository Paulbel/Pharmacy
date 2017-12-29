package by.pharmacy.service;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    List<User> showUsers(String administratorLogin, int number, int offset) throws ServiceException;

    void setUserRole(String administratorLogin, String userLogin, Role role) throws ServiceException;
}
