package by.optics.service.validator;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;

import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;
import by.optics.service.exception.UserExistsException;
import by.optics.service.exception.WrongPasswordException;

public class UserDataValidator {
    public void checkPassword(User user, String password) throws WrongPasswordException {
        String userPassword = user.getPassword();
        if(!userPassword.equals(password)){
            throw new WrongPasswordException("Incorrect data!");
        }
    }

    public void checkUnique(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            String login = user.getLogin();
            int userNumber = userDAO.findNumberOfUsersWithLogin(login);
            if(userNumber>0){
                throw new UserExistsException("User with login "+login+" already exists!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
