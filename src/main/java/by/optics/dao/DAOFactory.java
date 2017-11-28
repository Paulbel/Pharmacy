package by.optics.dao;

import by.optics.dao.impl.SQLAdminDAO;
import by.optics.dao.impl.SQLUserDAO;

public class DAOFactory {
    public static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new SQLUserDAO();
    private AdminDAO adminDAO = new SQLAdminDAO();

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }
}
