package by.pharmacy.service;

import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface ClientService {
    void addOrder(Order order) throws ServiceException;

    List<Order> getOrderList(String login, int number, int offset, Language language) throws ServiceException;

    int getOrderCount(String login, Language language) throws ServiceException;
}
