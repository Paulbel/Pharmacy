package by.pharmacy.dao;

import by.pharmacy.dao.impl.DrugDAOImpl;
import by.pharmacy.dao.impl.UserDAOImpl;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new DrugDAOImpl();

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DrugDAO getDrugDAO() {
        return drugDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }
}
