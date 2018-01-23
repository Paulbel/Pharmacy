package by.pharmacy.dao.impl;

import by.pharmacy.dao.ManufacturerDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAOImpl implements ManufacturerDAO {


    private final static String GET_COUNT = "SELECT" +
            " COUNT(id) AS 'count'" +
            "FROM manufacturer " +
            "INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id " +
            "INNER JOIN country ON manufacturer.country_code = country.code " +
            "INNER JOIN country_translate ON country.code = country_translate.country_code " +
            "WHERE manufacturer_translate.language_name = ? " +
            "AND country_translate.lan_name = manufacturer_translate.language_name ";


    private final static String ADD_MANUFACTURER =
            "INSERT INTO manufacturer (country_code, phone_number, email) VALUES (?,?,?);";

    private final static String ADD_MANUFACTURER_TRANSLATE =
            "INSERT INTO manufacturer_translate (language_name, manufacturer_id, name, address) VALUES (?,?,?,?);";

    private final static String GET_MANUFACTURERS = "SELECT" +
            " manufacturer.id," +
            " manufacturer.email," +
            " manufacturer.phone_number," +
            " manufacturer_translate.name," +
            " manufacturer_translate.address," +
            " country_translate.country_code," +
            " country_translate.name " +
            "FROM manufacturer " +
            "INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id " +
            "INNER JOIN country ON manufacturer.country_code = country.code " +
            "INNER JOIN country_translate ON country.code = country_translate.country_code " +
            "WHERE manufacturer_translate.language_name = ? " +
            "AND country_translate.lan_name = manufacturer_translate.language_name " +
            "LIMIT ? OFFSET ?;";

    private final static String GET_MANUFACTURER = "SELECT" +
            " manufacturer.id," +
            " manufacturer.email," +
            " manufacturer.phone_number," +
            " manufacturer_translate.name," +
            " manufacturer_translate.address," +
            " country_translate.country_code," +
            " country_translate.name " +
            "FROM manufacturer " +
            "INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id " +
            "INNER JOIN country ON manufacturer.country_code = country.code " +
            "INNER JOIN country_translate ON country.code = country_translate.country_code " +
            "WHERE manufacturer.id = ? AND manufacturer_translate.language_name = ? " +
            "AND country_translate.lan_name = manufacturer_translate.language_name";

    @Override
    public void addManufacturer(Manufacturer manufacturer, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement;
        try {
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(ADD_MANUFACTURER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, manufacturer.getCountry().getCode());
            statement.setString(2, manufacturer.getPhoneNumber());
            statement.setString(3, manufacturer.getEmail());


            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                statement = connection.prepareStatement(ADD_MANUFACTURER_TRANSLATE);

                statement.setString(1, language.toString().toLowerCase());
                statement.setInt(2, id);
                statement.setString(3, manufacturer.getName());
                statement.setString(4, manufacturer.getAddress());
                statement.executeUpdate();
            } else {
                throw new SQLException("Auto increment failed");
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //close statement
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.closeConnection(connection);
        }
    }


    @Override
    public List<Manufacturer> getManufacturers(Language language,int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_MANUFACTURERS)) {
            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, number);
            statement.setInt(3, offset);
            resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(createManufacturerFromResultSet(resultSet));
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DAOException(e);//спросить, нормально ли
                }
            }
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Manufacturer getManufacturer(int manufacturerId, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_MANUFACTURER)) {
            statement.setInt(1, manufacturerId);
            statement.setString(2,language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createManufacturerFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int getManufacturerCount(Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNT)) {
            statement.setString(1,language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    private Manufacturer createManufacturerFromResultSet(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        int id = resultSet.getInt("manufacturer.id");
        String email = resultSet.getString("manufacturer.email");
        String phoneNumber = resultSet.getString("manufacturer.phone_number");
        String name = resultSet.getString("manufacturer_translate.name");
        String address = resultSet.getString("manufacturer_translate.address");

        String countryName = resultSet.getString("country_translate.name");
        String countryCode = resultSet.getString("country_translate.country_code");


        manufacturer.setId(id);
        manufacturer.setAddress(address);
        manufacturer.setEmail(email);
        manufacturer.setPhoneNumber(phoneNumber);
        manufacturer.setName(name);

        Country country = new Country();

        country.setName(countryName);
        country.setCode(countryCode);

        manufacturer.setCountry(country);
        return manufacturer;
    }
}
