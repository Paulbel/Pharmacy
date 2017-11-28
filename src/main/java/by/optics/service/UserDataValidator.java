package by.optics.service;

import by.optics.entity.user.Role;
import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

public interface UserDataValidator {
    public void checkPassword(User user, String password) throws ServiceException;


    public void checkUnique(User user) throws ServiceException;



    public void checkAccessRight(int id, Role role) throws ServiceException;
}
