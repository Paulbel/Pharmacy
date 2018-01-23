package by.pharmacy.dao;

import by.pharmacy.dao.impl.*;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new DrugDAOImpl();
    private LanguageDAO languageDAO = new LanguageDAOImpl();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
    private CountryDAO countryDAO = new CountryDAOImpl();

    public ManufacturerDAO getManufacturerDAO() {
        return manufacturerDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DrugDAO getDrugDAO() {
        return drugDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
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
