package by.optics.service.validator;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;

import by.optics.entity.user.Role;
import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;
import by.optics.service.exception.UserExistsException;
import by.optics.service.exception.WrongPasswordException;

import javax.xml.bind.ValidationException;

public class UserDataValidator {
    public void checkPassword(User user, String password) throws WrongPasswordException {
        String userPassword = user.getPassword();
        if(!userPassword.equals(password)){
            throw new WrongPasswordException("Incorrect data!");
        }
    }

    public void checkUnique(User user) throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            UserDAO userDAO = factory.getUserDAO();
            String login = user.getLogin();
            int userNumber = userDAO.findNumberOfUsersWithLogin(login);
            if(userNumber>0){
                throw new UserExistsException("User with login "+login+" already exists!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }


    public void checkAccessRight(int id, Role role) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            User user = userDAO.findUserById(id);
            if (user.getRole() != role){
                throw new ServiceException();
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
