package by.optics.service;

import by.optics.entity.user.Role;
import by.optics.entity.user.User;
import by.optics.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    void banUser(int administratorId, int userId) throws ServiceException;
    void unbanUser(int administratorId, int userId) throws ServiceException;
    List<User> showUsers(int administratorId) throws ServiceException;
    void setUserRole(int adminId, int userId, Role role) throws ServiceException;
}
