package by.optics.service.impl;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;
import by.optics.service.AdministratorService;
import by.optics.service.exception.ServiceException;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {



    @Override
    public void banUser(int administratorId, String login) {

    }

    @Override
    public List<User> showUsers(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
