package by.pharmacy.service;

import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    void registration(User user) throws ServiceException;


}