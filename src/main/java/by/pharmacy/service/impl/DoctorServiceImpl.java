package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    @Override
    public void addPrescription(String doctorLogin, String clientLogin, int drugId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        /*daoFactory.get*/
    }

    @Override
    public List<User> getClientList() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.getUsers(Role.CLIENT);
        } catch (DAOException e) {
            throw new ServiceException("Can't find", e);
        }
    }
}
