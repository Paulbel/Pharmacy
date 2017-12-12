package by.pharmacy.service;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    List<User> showUsers(int administratorId) throws ServiceException;
    void setUserRole(int adminId, int userId, Role role) throws ServiceException;
}
