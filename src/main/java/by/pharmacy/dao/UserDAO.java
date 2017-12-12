package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;

import java.util.List;

public interface UserDAO {
    void registration(User user) throws DAOException;

    User findUserByLogin(String login) throws DAOException;

    int findNumberOfUsersWithLogin(String login) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    User findUserById(int id) throws DAOException;

    void setRole(int id, Role role) throws DAOException;
}
