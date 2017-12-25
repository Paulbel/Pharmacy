package by.pharmacy.dao.impl;

import by.pharmacy.dao.DAOConstant;
import by.pharmacy.dao.OrderDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLOrderDAO implements OrderDAO {

    private static final String FIND_USER_ORDERS = "SELECT\n" +
            "  drug_translate.name,\n" +
            "  drug.price,\n" +
            "  drug.id,\n" +
            "  orders.date,\n" +
            "  orders.number\n" +
            "FROM orders\n" +
            "  INNER JOIN user ON orders.client_login = user.login\n" +
            "  INNER JOIN drug ON orders.drug_id = drug.id\n" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id\n" +
            "WHERE\n" +
            "  user.login = ? AND drug_translate.lang_name = ?\n" +
            "LIMIT ? OFFSET ?;";
    private final static int FIND_USER_ORDERS_LOGIN_INDEX = 1;
    private final static int FIND_USER_ORDERS_LANGUAGE_INDEX = 2;
    private final static int FIND_USER_ORDERS_NUMBER = 3;
    private final static int FIND_USER_ORDERS_OFFSET = 4;


    @Override
    public void addOrder(String login, int number) throws DAOException {

    }

    @Override
    public List<Order> getOrders(String login, int number, int offset, Language language) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement(FIND_USER_ORDERS);

            statement.setString(FIND_USER_ORDERS_LOGIN_INDEX, login);
            statement.setString(FIND_USER_ORDERS_LANGUAGE_INDEX, language.name().toLowerCase());
            statement.setInt(FIND_USER_ORDERS_NUMBER, number);
            statement.setInt(FIND_USER_ORDERS_OFFSET, offset);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                Order order = new Order();
                Drug drug = new Drug();

                String drugName = set.getString(DAOConstant.DRUG_TRANSLATE_TABLE_NAME);
                double price = set.getDouble(DAOConstant.DRUG_TABLE_PRICE);
                Date date = set.getDate("orders.date");
                int drugNumber = set.getInt("drug.price");
                int drugId = set.getInt("drug.id");

                drug.setId(drugId);
                drug.setName(drugName);
                drug.setPrice(price);
                order.setDate(date);
                order.setNumber(drugNumber);
                orders.add(order);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrders(int number, int offset, Language language) throws DAOException {
        return null;
    }
}
