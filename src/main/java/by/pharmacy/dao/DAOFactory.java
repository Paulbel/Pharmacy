package by.pharmacy.dao;

import by.pharmacy.dao.impl.SQLDrugDAO;
import by.pharmacy.dao.impl.UserDAOImpl;


public class DAOFactory {
    public static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new SQLDrugDAO();

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
