package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.OrderDAO;
import by.pharmacy.dao.PrescriptionDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import by.pharmacy.entity.Prescription;
import by.pharmacy.service.ClientService;
import by.pharmacy.service.exception.AccessDeniedException;
import by.pharmacy.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void addOrder(Order order) throws ServiceException {
        int drugId = order.getDrug().getId();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        String clientLogin = order.getClient().getLogin();
        try {
            Drug drug = drugDAO.getDrugInfo(drugId);
            if (drug.isNeedPrescription()) {
                changePrescription(drugId, clientLogin, order.getNumber());
            }
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            orderDAO.addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void changePrescription(int drugId, String clientLogin, int number) throws ServiceException {
        try {
            PrescriptionDAO prescriptionDAO = daoFactory.getPrescriptionDAO();
            Prescription prescription = prescriptionDAO.getPrescriptionForDrug(drugId, clientLogin);
            if (prescription == null) {
                throw new AccessDeniedException("User doesn't have suitable prescription");
            }
            Date date = new Date();
            if (prescription.getEndDate().before(date)) {
                throw new AccessDeniedException("User has expired prescription");
            }

            int nextNumber = prescription.getNumber() - number;
            if (nextNumber < 0) {
                throw new AccessDeniedException("Not enough drugs at stock");
            } else if (nextNumber == 0) {
                prescriptionDAO.removePrescription(prescription.getId());
            } else {
                prescriptionDAO.changePrescriptionDrugNumber(prescription.getId(), nextNumber);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Order> getOrderList(String login, int number, int offset, Language language) throws ServiceException {
        try {
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            return orderDAO.getClientOrderList(login, number, offset, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getOrderCount(String login, Language language) throws ServiceException {
        try {
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            return orderDAO.getOrderCount(login, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getPrescriptionList(String login, Language language) throws ServiceException {
        try {
            PrescriptionDAO prescriptionDAO = daoFactory.getPrescriptionDAO();
            return prescriptionDAO.getPrescriptionList(login, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
