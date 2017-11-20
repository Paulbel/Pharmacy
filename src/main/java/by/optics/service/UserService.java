package by.optics.service;

import by.optics.dao.DAOFactory;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.User;
import by.optics.service.exception.ServiceException;
import by.optics.service.exception.WrongPasswordException;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    void registration(User user);
}
