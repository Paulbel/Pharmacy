package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;

import java.util.List;

public interface UserDAO {
    void registration(User user) throws DAOException;

    User findUserByLogin(String login) throws DAOException;

    List<User> getUsers(int number, int offset) throws DAOException;


    void setRole(String login, Role role) throws DAOException;
}
