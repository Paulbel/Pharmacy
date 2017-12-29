package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(String login, int drugId, int number) throws DAOException;

    List<Order> getUserOrders(String login, int number, int offset, Language language) throws DAOException;

    List<Order> getOrders(int number, int offset, Language language) throws DAOException;
}
