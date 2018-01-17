package by.pharmacy.service.impl;

import by.pharmacy.dao.*;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.entity.User;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {


    @Override
    public User signIn(String login, String password) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public void signUp(User user, String password) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.registration(user, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.getDrugs(language, number, offset);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drugs", e);
        }
    }

    @Override
    public List<Drug> findDrug(String name, Language language) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.findDrugByName(name, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't find drug", e);
        }
    }

    @Override
    public int getDrugNumber() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrugNumber();
        } catch (DAOException e) {
            throw new ServiceException("Can't get entries number", e);
        }
    }

    @Override
    public Map<String, Language> getLanguages(Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        LanguageDAO languageDAO = daoFactory.getLanguageDAO();
        try {
            return languageDAO.getLanguages(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get languages", e);
        }
    }

    @Override
    public List<Manufacturer> getManufacturers(Language language, int number, int offset) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturers(language, number, offset);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public int getManufacturerNumber() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturerCount();
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public Drug getDrug(int drugId, Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrug(drugId, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }


}
