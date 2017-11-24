package by.optics.service;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;
import by.optics.service.exception.WrongPasswordException;

public class UserDataValidator {
    public void checkPassword(User user, String password) throws WrongPasswordException {
        String userPassword = user.getPassword();
        if(!userPassword.equals(password)){
            throw new WrongPasswordException();
        }
    }

    public void checkUnique(User user){
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            userDAO.findUserByLogin(user.getLogin());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
