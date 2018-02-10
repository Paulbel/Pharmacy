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
import by.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public void addOrder(Order order) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        int drugId = order.getDrug().getId();
        DrugDAO drugDAO = daoFactory.getDrugDAO();
        String clientLogin = order.getClient().getLogin();
        try {
            Drug drug = drugDAO.getDrugInfo(drugId);
            if (drug.isNeedPrescription()) {
                PrescriptionDAO prescriptionDAO = daoFactory.getPrescriptionDAO();
                Prescription prescription = prescriptionDAO.getPrescriptionForDrug(drugId, clientLogin);
                Date date = new Date();
                if (prescription.getEndDate().after(date)) {
                    System.out.println("cannot");
                }
            }
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            orderDAO.addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrderList(String login, int number, int offset, Language language) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            return orderDAO.getClientOrderList(login, number, offset, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getOrderCount(String login, Language language) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            return orderDAO.getOrderCount(login, language);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
