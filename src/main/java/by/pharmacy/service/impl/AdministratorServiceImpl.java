package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.validator.UserDataValidator;
import by.pharmacy.validator.ValidatorFactory;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
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
            validator.checkAccessRight(adminId, Role.ADMIN);

            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.setRole(userId, role);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
