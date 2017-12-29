package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;
import by.pharmacy.service.exception.WrongPasswordException;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

public class UserServiceImpl implements UserService {


    @Override
    public User signIn(String login, String password) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findUserByLogin(login);
            if(!user.getPassword().equals(password)){
                throw new WrongPasswordException();
            }
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public void registration(User user) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
