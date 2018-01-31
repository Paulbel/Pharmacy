package by.pharmacy.dao;

import by.pharmacy.dao.impl.country.CountryDAOImpl;
import by.pharmacy.dao.impl.drug.DrugDAOImpl;
import by.pharmacy.dao.impl.language.LanguageDAOImpl;
import by.pharmacy.dao.impl.manufacturer.ManufacturerDAOImpl;
import by.pharmacy.dao.impl.order.OrderDAOImpl;
import by.pharmacy.dao.impl.prescription.PrescriptionDAOImpl;
import by.pharmacy.dao.impl.user.UserDAOImpl;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new DrugDAOImpl();
    private LanguageDAO languageDAO = new LanguageDAOImpl();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
    private CountryDAO countryDAO = new CountryDAOImpl();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public PrescriptionDAO getPrescriptionDAO() {
        return prescriptionDAO;
    }

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
