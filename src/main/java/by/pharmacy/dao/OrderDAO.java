package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import by.pharmacy.entity.User;

import java.util.List;

public interface OrderDAO {
    void addOrder(String login, int number) throws DAOException;

    List<Order> getOrders (String login, int number, int offset, Language language) throws DAOException;

    List<Order> getOrders (int number, int offset, Language language) throws DAOException;
}
