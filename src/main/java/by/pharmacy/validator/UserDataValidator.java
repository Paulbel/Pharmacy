package by.pharmacy.validator;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

public interface UserDataValidator {
    public void checkPassword(User user, String password) throws ServiceException;


    public void checkUnique(User user) throws ServiceException;



    public void checkAccessRight(int id, Role role) throws ServiceException;
}
