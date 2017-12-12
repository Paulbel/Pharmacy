package by.pharmacy.validator.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.ServiceException;
import by.pharmacy.service.exception.UserExistsException;
import by.pharmacy.service.exception.WrongPasswordException;
import by.pharmacy.validator.UserDataValidator;

public class UserDataValidatorImpl implements UserDataValidator {
    public UserDataValidatorImpl(){}

    public void checkPassword(User user, String password) throws WrongPasswordException {
        String userPassword = user.getPassword();
        if(!userPassword.equals(password)){
            throw new WrongPasswordException("Incorrect data!");
        }
    }



    public void checkAccessRight(int id, Role role) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            User user = userDAO.findUserById(id);
            if (user.getRole() != role){
                throw new AccessDeniedException("You have no rights for doing it!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }

    }

}
