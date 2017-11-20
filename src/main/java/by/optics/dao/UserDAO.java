package by.optics.dao;

import by.optics.dao.exception.DAOException;
import by.optics.dao.exception.NoUserFoundException;
import by.optics.entity.User;

public interface UserDAO {
    void registration(User user) throws DAOException;
    User findUser(int id);
    User findUser(String name, String surname, String patronymic);
    User findUser(String login) throws DAOException;

}
