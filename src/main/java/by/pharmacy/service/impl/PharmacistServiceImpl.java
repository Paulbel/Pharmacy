package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

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
    public void removeDrug(int drugId) {

    }

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.getDrugs(language,number,offset);
        } catch (DAOException e) {
            throw new ServiceException("Can't get drugs",e);
        }
    }

    @Override
    public int getDrugNumber() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        try {
            return drugDAO.getDrugNumber();
        } catch (DAOException e) {
            throw new ServiceException("Can't get entries number",e);
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


}
