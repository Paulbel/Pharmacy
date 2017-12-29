package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.exception.ServiceException;

public class DoctorServiceImpl implements DoctorService {
    @Override
    public void addPrescription(String doctorLogin, String clientLogin, int drugId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        /*daoFactory.get*/
    }
}
