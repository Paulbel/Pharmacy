package by.optics.dao;

import by.optics.dao.exception.DAOException;
import by.optics.entity.user.Role;
import by.optics.entity.user.User;

import java.util.List;

public interface UserDAO {
    void registration(User user) throws DAOException;

    User findUserByLogin(String login) throws DAOException;

    int findNumberOfUsersWithLogin(String login) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    User findUserById(int id) throws DAOException;

    void setBanned(int id, boolean condition) throws DAOException;

    void setRole(int id, Role role) throws DAOException;
}
