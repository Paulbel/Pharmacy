package by.pharmacy.service.impl;

import by.pharmacy.dao.*;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.*;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String login, String password) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.signIn(login, password);
            if (user == null) {
                throw new AccessDeniedException("User with login " + login + " hasn't signed in yet");
            }
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void signUp(User user, String password) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.signUp(user, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset, DrugCriteria orderField) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();

            return drugDAO.getDrugList(language, number, offset, orderField);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drugs", e);
        }
    }

    @Override
    public List<Drug> findDrug(String name, Language language, DrugCriteria orderField) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.findDrugByName(name, language, orderField);
        } catch (DAOException e) {
            throw new ServiceException("Can't find drug", e);
        }
    }

    @Override
    public int getDrugCount(Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrugCount(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get entries number", e);
        }
    }

    @Override
    public Map<String, Language> getLanguages(Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        LanguageDAO languageDAO = daoFactory.getLanguageDAO();
        try {
            return languageDAO.getLanguageMap(language);
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
    public int getManufacturerCount(Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturerCount(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public List<Manufacturer> findManufacturer(String content, Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.findManufacturer(content, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public Manufacturer getManufacturer(int id, Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturer(id, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturer", e);
        }
    }

    @Override
    public Drug getDrug(int drugId, Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrug(drugId, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drug", e);
        }
    }

    @Override
    public List<Country> getCountryList(Language language) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CountryDAO countryDAO = daoFactory.getCountryDAO();
        try {
            return countryDAO.getCountryList(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drug", e);
        }
    }


}
