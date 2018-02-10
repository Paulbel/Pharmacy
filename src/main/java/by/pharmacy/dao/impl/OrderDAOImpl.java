package by.pharmacy.dao.impl;

import by.pharmacy.dao.OrderDAO;
import by.pharmacy.dao.builder.OrderBuilder;
import by.pharmacy.dao.builder.impl.OrderBuilderImpl;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private static final String GET_ORDERS_LIST = "SELECT" +
            "  user.login," +
            "  drug_translate.name," +
            "  drug.price," +
            "  drug.id," +
            "  orders.id," +
            "  orders.number," +
            "  orders.date" +
            " FROM orders" +
            " INNER JOIN user ON orders.client_login = user.login" +
            " INNER JOIN drug ON orders.drug_id = drug.id" +
            " INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            " INNER JOIN user ON orders.client_login = user.login" +
            " WHERE" +
            " drug_translate.lang_name = ?" +
            " LIMIT ? OFFSET ?;";
    private static final String ADD_ORDER = "INSERT INTO orders (client_login, drug_id, number, date) VALUES (?,?,?,?);";
    private static final String FIND_CLIENT_ORDER_LIST = "SELECT" +
            "  drug_translate.name," +
            "  drug.price," +
            "  orders.id," +
            "  drug.id," +
            "  orders.date," +
            "  orders.number" +
            " FROM orders" +
            "  INNER JOIN user ON orders.client_login = user.login" +
            "  INNER JOIN drug ON orders.drug_id = drug.id" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            " WHERE" +
            "  user.login = ? AND drug_translate.lang_name = ?" +
            " LIMIT ? OFFSET ?;";
    private static final String COUNT_CLIENT_ORDER = "SELECT" +
            "  COUNT(orders.id) AS 'count'" +
            " FROM orders" +
            "  INNER JOIN user ON orders.client_login = user.login" +
            "  INNER JOIN drug ON orders.drug_id = drug.id" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            " WHERE" +
            "  user.login = ? AND drug_translate.lang_name = ?;";

    @Override
    public void addOrder(Order order) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_ORDER);
            statement.setString(1, order.getClient().getLogin());
            statement.setInt(2, order.getDrug().getId());
            statement.setInt(3, order.getNumber());
            long currentTime = System.currentTimeMillis();
            statement.setTimestamp(4, new Timestamp(currentTime));

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add order to db", e);
            throw new DAOException("An error has occurred in attempt of adding order to database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> getClientOrderList(String login, int number, int offset, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_ORDER_LIST)) {
            statement.setString(1, login);
            statement.setString(2, language.name().toLowerCase());
            statement.setInt(3, number);
            statement.setInt(4, offset);

            ResultSet set = statement.executeQuery();


            List<Order> orders = new ArrayList<>();
            OrderBuilder orderBuilder = new OrderBuilderImpl(set);

            while (set.next()) {
                orderBuilder.createOrder();
                orderBuilder.buildDrug();
                orderBuilder.buildOrderInfo();
                orders.add(orderBuilder.getOrder());
            }
            return orders;
        } catch (SQLException e) {
            logger.error("Not able to get orders of client with login = " + login, e);
            throw new DAOException("An error has occurred in attempt of getting orders of client", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Order> getOrderList(int number, int offset, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDERS_LIST)) {
            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, number);
            statement.setInt(3, offset);
            resultSet = statement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            OrderBuilder orderBuilder = new OrderBuilderImpl(resultSet);

            while (resultSet.next()) {
                orderBuilder.buildFullOrder();
                orderList.add(orderBuilder.getOrder());
            }
            return orderList;
        } catch (SQLException e) {
            logger.error("Not able to get orders from database", e);
            throw new DAOException("An error has occurred in attempt of getting orders from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int getOrderCount(String login, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(COUNT_CLIENT_ORDER)) {
            statement.setString(1, login);
            statement.setString(2, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            logger.error("Not able to get order count", e);
            throw new DAOException("An error has occurred in attempt of getting order count", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
