package by.optics.service;

import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    void banUser(int id) throws DAOException;
    void unBanUser(int id) throws DAOException;
    List<User> showUsers() throws ServiceException;
}
