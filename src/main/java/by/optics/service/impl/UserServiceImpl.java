package by.optics.service.impl;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.Client;
import by.optics.entity.User;
import by.optics.service.ClientService;
import by.optics.service.UserService;
import by.optics.service.exception.ServiceException;
import by.optics.service.exception.WrongPasswordException;

public class UserServiceImpl implements UserService{


    @Override
    public User signIn(String login, String password) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        User user;
        try {
            User foundUser = userDAO.findUser(login);

            //TODO validation
            if(!foundUser.getPassword().equals(password)){
                throw new WrongPasswordException();
            }
            user = foundUser;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }


    @Override
    public void registration(User user) {
        //TODO validation that checks parameters and if user with this login and email exists
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.registration(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
