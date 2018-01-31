package by.pharmacy.dao.impl.order;

import by.pharmacy.dao.OrderDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import by.pharmacy.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String GET_ORDERS = "SELECT" +
            "  user.login," +
            "  drug_translate.name," +
            "  drug.price," +
            "  drug.id," +
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

    private static final String FIND_USER_ORDERS = "SELECT" +
            "  drug_translate.name," +
            "  drug.price," +
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



    @Override
    public void addOrder(Order order) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_ORDER);
            statement.setString(1, order.getClient().getLogin());
            statement.setInt(2, order.getDrug().getId());
            statement.setInt(3, order.getDrug().getNumber());
            long currentTime = System.currentTimeMillis();
            statement.setDate(4, new Date(currentTime));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getUserOrders(String login, int number, int offset, Language language) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(FIND_USER_ORDERS)) {


            statement.setString(1, login);
            statement.setString(2, language.name().toLowerCase());
            statement.setInt(3, number);
            statement.setInt(4, offset);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Order order = new Order();
                Drug drug = new Drug();

                String drugName = set.getString("drug_translate");
                double price = set.getDouble("drug.price");
                Date date = set.getDate("orders.date");
                int drugNumber = set.getInt("order.number");
                int drugId = set.getInt("drug.id");

                drug.setId(drugId);
                drug.setName(drugName);
                drug.setPrice(price);
                order.setDate(date);
                order.setNumber(drugNumber);
                orders.add(order);
            }
            if (orders.size() == 0) {
                throw new DAOException("No orders found");
            }

        } catch (SQLException e) {
            throw new DAOException("Can't connect to DB");
        }
        return orders;
    }


    @Override
    public List<Order> getOrders(int number, int offset, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet set = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDERS)) {
            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, number);
            statement.setInt(3, offset);
            set = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (set.next()) {
                Order order = new Order();
                String login = set.getString("user.login");
                User user = new User();
                user.setLogin(login);

                Drug drug = new Drug();
                double price = set.getDouble("drug.price");
                String drugName = set.getString("drug_translate.name");
                drug.setPrice(price);
                drug.setName(drugName);

                int drugNumber = set.getInt("orders.number");
                java.util.Date date = set.getDate("orders.date");

                order.setClient(user);
                order.setDrug(drug);
                order.setDate(date);
                order.setNumber(drugNumber);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            if(set!= null){
                try {
                    set.close();
                } catch (SQLException e) {
                    throw new DAOException(e);//спросить, нормально ли
                }
            }
            connectionPool.closeConnection(connection);
        }
    }
}
