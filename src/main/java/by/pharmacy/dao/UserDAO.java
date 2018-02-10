package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;

import java.util.List;

public interface UserDAO {
    void signUp(User user, String password) throws DAOException;

    User findUserByLogin(String login) throws DAOException;

    List<User> getUserList(int number, int offset) throws DAOException;

    void setRole(String login, UserRole role) throws DAOException;

    User signIn(String login, String password) throws DAOException;

    List<User> getUserList(UserRole role) throws DAOException;
}
