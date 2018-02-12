package by.pharmacy.service;

import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

/**
 * Service layer interface for administrator activity options.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface AdministratorService {
    /**
     * Returns list of users.
     *
     * @param administratorLogin login of administrator, who initiated getting user list.
     * @param number             max number of users which will be returned.
     * @param offset             offset from start of data source.
     * @return list if users.
     * @throws ServiceException if there is no admin with specified login, or number or offset are less than 0.
     */
    List<User> getUserList(String administratorLogin, int number, int offset) throws ServiceException;

    /**
     * Sets new role to user.
     *
     * @param administratorLogin login of administrator, who initiated role setting.
     * @param userLogin          login of user for role changing.
     * @param role               new role of user.
     * @throws ServiceException if there is no admin with specified login.
     */
    void setUserRole(String administratorLogin, String userLogin, UserRole role) throws ServiceException;


    /**
     * Returns user with specific login.
     *
     * @param administratorLogin login of administrator.
     * @param login              user login.
     * @return user or null if user not exists.
     * @throws ServiceException if there is no admin with specified login.
     */
    User getUser(String administratorLogin, String login) throws ServiceException;
}
