package by.optics.dao;

import by.optics.dao.impl.SQLUserDAO;

public class DAOFactory {
    public static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new SQLUserDAO();


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
    private DAOFactory(){}
}
