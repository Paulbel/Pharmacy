package by.optics.dao;

import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;

import java.util.List;

public interface UserDAO {
    void registration(User user) throws DAOException;

    User findUserByLogin(String login) throws DAOException;

    int findNumberOfUsersWithLogin(String login) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    void setBanned(int id,boolean condition) throws DAOException;
}
