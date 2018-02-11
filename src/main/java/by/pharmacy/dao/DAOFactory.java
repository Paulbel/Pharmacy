package by.pharmacy.dao;

import by.pharmacy.dao.impl.*;

/**
 * Factory class for granting access to data access objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private DrugDAO drugDAO = new DrugDAOImpl();
    private LanguageDAO languageDAO = new LanguageDAOImpl();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
    private CountryDAO countryDAO = new CountryDAOImpl();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private ProlongationRequestDAO prolongationRequestDAO = new ProlongationRequestDAOImpl();


    public ProlongationRequestDAO getProlongationRequestDAO() {
        return prolongationRequestDAO;
    }

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
