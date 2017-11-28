package by.optics.service.impl;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.Role;
import by.optics.entity.user.User;
import by.optics.service.AdministratorService;
import by.optics.service.validator.UserDataValidator;
import by.optics.service.ValidatorFactory;
import by.optics.service.exception.ServiceException;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {


    @Override
    public void banUser(int administratorId, int userId) throws ServiceException {
        try {
            checkBanUnbanAccessRights(administratorId, userId);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();

            userDAO.setBanned(userId, true);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void unbanUser(int administratorId, int userId) throws ServiceException {
        try {
            checkBanUnbanAccessRights(administratorId, userId);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.setBanned(userId, false);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    private void checkBanUnbanAccessRights(int adminId, int userId) throws ServiceException {
        ValidatorFactory factory = ValidatorFactory.getInstance();
        UserDataValidator validator = factory.getUserDataValidator();
        validator.checkAccessRight(adminId, Role.ADMIN);
        validator.checkAccessRight(userId, Role.USER);
    }

    @Override
    public List<User> showUsers(int id) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void setUserRole(int adminId, int userId, Role role) throws ServiceException {
        try {
            ValidatorFactory factory = ValidatorFactory.getInstance();
            UserDataValidator validator = factory.getUserDataValidator();

            switch (role) {
                case DOCTOR:
                    validator.checkAccessRight(userId, Role.USER);
                    break;
                case USER:
                    validator.checkAccessRight(userId, Role.DOCTOR);
                    break;
            }
            validator.checkAccessRight(adminId, Role.ADMIN);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.setRole(userId, role);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
