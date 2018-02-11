package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;

import java.util.List;


/**
 * Data access object interface, which is intended to work with {@link Order} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface OrderDAO {

    /**
     * Creates order entry in data source.
     *
     * @param order has info about entry which is being added.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addOrder(Order order) throws DAOException;

    /**
     * Returns order list of specified client from data source.
     *
     * @param login    login of client.
     * @param number   max number of entries which return value has.
     * @param offset   number of entries from the start point of data source, which will be skipped.
     * @param language object of class {@link Language} which represents locale of return value.
     * @return object of class {@link List} which has all {@link Order} objects that were found.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Order> getClientOrderList(String login, int number, int offset, Language language) throws DAOException;

    /**
     * Returns order list from data source.
     *
     * @param number   max number of entries which return value has.
     * @param offset   number of entries from the start point of data source, which will be skipped.
     * @param language object of class {@link Language} which represents locale of return value.
     * @return object of class {@link List} which has all {@link Order} objects that were found.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Order> getOrderList(int number, int offset, Language language) throws DAOException;

    /**
     * Returns count of orders in data source with locale info.
     *
     * @param login    login of client.
     * @param language object of class {@link Language} which represents locale of return value.
     * @return number of order entries in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    int getOrderCount(String login, Language language) throws DAOException;
}
