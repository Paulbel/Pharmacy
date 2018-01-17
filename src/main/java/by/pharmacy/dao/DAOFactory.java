package by.pharmacy.dao;

import by.pharmacy.dao.impl.DrugDAOImpl;
import by.pharmacy.dao.impl.LanguageDAOImpl;
import by.pharmacy.dao.impl.ManufacturerDAOImpl;
import by.pharmacy.dao.impl.UserDAOImpl;
import by.pharmacy.entity.Manufacturer;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new DrugDAOImpl();
    private LanguageDAO languageDAO = new LanguageDAOImpl();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();

    public ManufacturerDAO getManufacturerDAO() {
        return manufacturerDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DrugDAO getDrugDAO() {
        return drugDAO;
    }


    public LanguageDAO getLanguageDAO() {
        return languageDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }
}
