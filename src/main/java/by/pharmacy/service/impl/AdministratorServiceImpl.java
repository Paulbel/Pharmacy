package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.validator.ValidatorFactory;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {


    @Override
    public List<User> getUserList(String administratorLogin, int number, int offset) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            User administrator =  userDAO.findUserByLogin(administratorLogin);
            if(!administrator.getRole().equals(Role.ADMIN)){
                throw new AccessDeniedException("User must be an administrator");
            }
            return userDAO.getUsers(number,offset);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void setUserRole(String administratorLogin, String userLogin, Role role) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            User administrator =  userDAO.findUserByLogin(administratorLogin);
            if(!administrator.getRole().equals(Role.ADMIN)){
                throw new AccessDeniedException("User must be an administrator");
            }
            userDAO.setRole(userLogin, role);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
