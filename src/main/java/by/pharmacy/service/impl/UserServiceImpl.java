package by.pharmacy.service.impl;

import by.pharmacy.dao.*;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.*;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.InvalidDataException;
import by.pharmacy.service.exception.ServiceException;
import by.pharmacy.service.validator.Validator;
import by.pharmacy.service.validator.ValidatorFactory;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final static DAOFactory daoFactory = DAOFactory.getInstance();


    @Override
    public User signIn(String login, String password) throws ServiceException {
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.signIn(login, password);
            if (user == null) {
                throw new AccessDeniedException("User with login " + login + " hasn't signed in yet");
            }
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signUp(User user, String password) throws ServiceException {
        try {
            ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
            Validator userValidator = validatorFactory.getValidator(ValidatorFactory.EntityType.USER);
            if (!userValidator.validate(user)) {
                throw new InvalidDataException("Invalid user data");
            }
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.signUp(user, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset, DrugCriteria orderField) throws ServiceException {
        try {
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.getDrugList(language, number, offset, orderField);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drugs from dao layer", e);
        }
    }

    @Override
    public List<Drug> findDrug(String name, Language language, DrugCriteria orderField) throws ServiceException {
        try {
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.findDrugByName(name, language, orderField);
        } catch (DAOException e) {
            throw new ServiceException("Can't find specific drug,", e);
        }
    }

    @Override
    public int getDrugCount(Language language) throws ServiceException {
        try {
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.getDrugCount(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get entries number", e);
        }
    }

    @Override
    public Map<String, Language> getLanguages(Language language) throws ServiceException {
        try {
            LanguageDAO languageDAO = daoFactory.getLanguageDAO();
            return languageDAO.getLanguageMap(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get languages", e);
        }
    }

    @Override
    public List<Manufacturer> getManufacturers(Language language, int number, int offset) throws ServiceException {
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturerList(language, number, offset);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public int getManufacturerCount(Language language) throws ServiceException {
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturerCount(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public List<Manufacturer> findManufacturer(String content, Language language) throws ServiceException {
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.findManufacturer(content, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturers", e);
        }
    }

    @Override
    public Manufacturer getManufacturer(int id, Language language) throws ServiceException {
        ManufacturerDAO manufacturerDAO = daoFactory.getManufacturerDAO();
        try {
            return manufacturerDAO.getManufacturer(id, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get manufacturer", e);
        }
    }

    @Override
    public Drug getDrug(int drugId, Language language) throws ServiceException {
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrug(drugId, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drug", e);
        }
    }

    @Override
    public List<Country> getCountryList(Language language) throws ServiceException {
        try {
            CountryDAO countryDAO = daoFactory.getCountryDAO();
            return countryDAO.getCountryList(language);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drug", e);
        }
    }


}
