package by.optics.service;

import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    void banUser(int administratorId, String login);

    List<User> showUsers(int administratorId) throws ServiceException;
}
