package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.PrescriptionDAO;
import by.pharmacy.dao.ProlongationRequestDAO;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.ProlongationRequestStatus;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    @Override
    public void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User doctor = userDAO.findUserByLogin(doctorLogin);
            User client = userDAO.findUserByLogin(clientLogin);
            if (doctor.getRole() != UserRole.DOCTOR) {
                throw new ServiceException();
            }
            if (client.getRole() != UserRole.CLIENT) {
                throw new ServiceException();
            }
            if (dayCount <= 0) {
                throw new ServiceException();
            }
            PrescriptionDAO prescriptionDAO = daoFactory.getPrescriptionDAO();
            prescriptionDAO.addPrescription(doctorLogin, clientLogin, drug, dayCount);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getClientList() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            return userDAO.getUserList(UserRole.CLIENT);
        } catch (DAOException e) {
            throw new ServiceException("Can't find", e);
        }
    }

    @Override
    public void changeProlongationRequestStatus(String idString, String doctorLogin, String statusString) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ProlongationRequestDAO prolongationRequestDAO = daoFactory.getProlongationRequestDAO();
            ProlongationRequestStatus status = ProlongationRequestStatus.valueOf(statusString);
            long prolongationRequestId = Long.valueOf(idString);
            prolongationRequestDAO.changeProlongationRequestStatus(prolongationRequestId, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
