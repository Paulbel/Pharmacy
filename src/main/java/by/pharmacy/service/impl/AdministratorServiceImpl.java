package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private final static DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public List<User> getUserList(String administratorLogin, int number, int offset) throws ServiceException {
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            User administrator = userDAO.findUserByLogin(administratorLogin);
            if (administrator.getRole() != (UserRole.ADMIN)) {
                throw new AccessDeniedException("User must be an administrator");
            }
            return userDAO.getUserList(number, offset);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void setUserRole(String administratorLogin, String userLogin, UserRole role) throws ServiceException {
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            User administrator = userDAO.findUserByLogin(administratorLogin);
            if (administrator.getRole() != UserRole.ADMIN) {
                throw new AccessDeniedException("User must be an administrator");
            }
            userDAO.setRole(userLogin, role);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User getUser(String administratorLogin, String login) throws ServiceException {
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            User admin = userDAO.findUserByLogin(administratorLogin);
            if (admin.getRole() != UserRole.ADMIN) {
                throw new AccessDeniedException("User must be an administrator");
            }
            return userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
