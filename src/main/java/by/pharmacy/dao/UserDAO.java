package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;

import java.util.List;

/**
 * Data access object interface, which is intended to work with by.pharmacy.entity.User objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface UserDAO {
    /**
     * Creates user entry in data source.
     *
     * @param user     has data for being added.
     * @param password user password.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void signUp(User user, String password) throws DAOException;

    /**
     * Returns user with login param value.
     *
     * @param login login of user.
     * @return object of class {@link User} which has specified login, or null if user doesn't exist in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    User findUserByLogin(String login) throws DAOException;

    /**
     * Returns list of users from data source.
     *
     * @param number max number of entries which return value has.
     * @param offset number of entries from the start point of data source, which will be skipped.
     * @return
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<User> getUserList(int number, int offset) throws DAOException;

    /**
     * Sets specified role to user.
     *
     * @param login user login.
     * @param role  new user role.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void setRole(String login, UserRole role) throws DAOException;

    /**
     * Returns object of {@link User} class if user with login param and password param exists.
     *
     * @param login    login of user.
     * @param password user's password.
     * @return object of {@link User} class if user with login param and password param exists or null if doesn't.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    User signIn(String login, String password) throws DAOException;

    /**
     * Returns list of users with specific role.
     *
     * @param role role of users.
     * @return object of {@link List} class which has {@link User}.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<User> getUserList(UserRole role) throws DAOException;
}
