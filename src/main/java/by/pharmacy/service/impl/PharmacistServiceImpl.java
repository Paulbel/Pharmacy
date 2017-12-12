package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class PharmacistServiceImpl implements PharmacistService {
    @Override
    public void addDrug(int drugId) {

    }

    @Override
    public void removeDrug(int drugId) {

    }

    @Override
    public List<Drug> getAllDrugs(Language language) throws ServiceException {
        try {
            //TODO: validation if user is doctor
            DAOFactory daoFactory = DAOFactory.getInstance();
            DrugDAO drugDAO = daoFactory.getDrugDAO();
            return drugDAO.getAll(language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
