package by.pharmacy.service;

import by.pharmacy.entity.Order;
import by.pharmacy.service.exception.ServiceException;

public interface ClientService {
    void addOrder(Order order) throws ServiceException;
}
