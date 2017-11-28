package by.optics.service;

import by.optics.entity.Role;
import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    void registration(User user) throws ServiceException;
    Role getUsersRole(int userId) throws ServiceException;
}
