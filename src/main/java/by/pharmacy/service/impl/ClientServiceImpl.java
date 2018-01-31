package by.pharmacy.service.impl;

import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.OrderDAO;
import by.pharmacy.entity.Order;
import by.pharmacy.service.ClientService;
import by.pharmacy.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class ClientServiceImpl implements ClientService {


    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public void addOrder(Order order) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();

        /*orderDAO.addOrder();*/
    }
}
