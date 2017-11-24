package by.optics.dao;

import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;

public interface UserDAO {
    void registration(User user) throws DAOException;
    User findUserByLogin(String login) throws DAOException;
}
