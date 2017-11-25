package by.optics.service;

import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    void banUser(String login);

    List<User> showUsers() throws ServiceException;
}
