package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.ManufacturerDAO;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.*;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.exception.ServiceException;

public class PharmacistServiceImpl implements PharmacistService {
    @Override
    public void addDrug(Drug drug, Language language) throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            DrugDAO drugDAO = factory.getDrugDAO();
            drugDAO.addDrug(drug,language);
        } catch (DAOException e) {
            throw new ServiceException("Can't add drug");
        }
    }

    @Override
    public void addDrugDescription(String pharmacistLogin, Drug drug, Language language) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findUserByLogin(pharmacistLogin);
            if(!user.getRole().equals(Role.PHARMACIST)){
                throw new ServiceException("User must be a pharmacist");
            }
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            drugDAO.addDrugDescription(drug,language);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeDrugDescription(Drug drug, Language language) throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            DrugDAO drugDAO = factory.getDrugDAO();
            drugDAO.changeDrugDescription(drug,language);
        } catch (DAOException e) {
            throw new ServiceException("Can't change");
        }
    }

    @Override
    public void changeDrugInfo(Drug drug) throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            DrugDAO drugDAO = factory.getDrugDAO();
            drugDAO.changeDrugInfo(drug);
        } catch (DAOException e) {
            throw new ServiceException("Can't change");
        }
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer, Language language) throws ServiceException {
        try {
            DAOFactory factory = DAOFactory.getInstance();
            ManufacturerDAO manufacturerDAO = factory.getManufacturerDAO();
            manufacturerDAO.addManufacturer(manufacturer, language);
        } catch (DAOException e) {
            throw new ServiceException("Can't add");
        }
    }

    @Override
    public void removeDrug(int drugId, String doctorLogin) throws ServiceException {

    }

}
