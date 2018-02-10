package by.pharmacy.dao.impl;

import by.pharmacy.dao.ManufacturerDAO;
import by.pharmacy.dao.builder.ManufacturerBuilder;
import by.pharmacy.dao.builder.impl.ManufacturerBuilderImpl;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAOImpl implements ManufacturerDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private final static String GET_COUNT = "SELECT" +
            " COUNT(id) AS 'count'" +
            "FROM manufacturer " +
            "INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id " +
            "INNER JOIN country ON manufacturer.country_code = country.code " +
            "INNER JOIN country_translate ON country.code = country_translate.country_code " +
            "WHERE manufacturer_translate.language_name = ? " +
            "AND country_translate.lan_name = manufacturer_translate.language_name ";
    private final static String ADD_MANUFACTURER = "INSERT INTO manufacturer (country_code, phone_number, email) VALUES (?,?,?);";
    private final static String ADD_MANUFACTURER_TRANSLATE = "INSERT INTO manufacturer_translate (language_name, manufacturer_id, name, address) " +
            "VALUES (?,?,?,?);";
    private final static String FIND_MANUFACTURER = "SELECT" +
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
            "WHERE manufacturer_translate.name LIKE ? " +
            "AND manufacturer_translate.language_name = ? " +
            "AND country_translate.lan_name = manufacturer_translate.language_name " +
            " ORDER BY manufacturer_translate.name;";
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
        try {
            addManufacturerUsingTransaction(manufacturer, language);
        } catch (SQLException e) {
            logger.error("Not able to add manufacturer to db", e);
            throw new DAOException("An error has occurred in attempt of adding manufacturer to database", e);
        }
    }

    private void addManufacturerUsingTransaction(Manufacturer manufacturer, Language language) throws DAOException, SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement addStatement = connection.prepareStatement(ADD_MANUFACTURER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addDescriptionStatement = connection.prepareStatement(ADD_MANUFACTURER_TRANSLATE)) {
            connection.setAutoCommit(false);

            addStatement.setString(1, manufacturer.getCountry().getCode());
            addStatement.setString(2, manufacturer.getPhoneNumber());
            addStatement.setString(3, manufacturer.getEmail());

            addStatement.executeUpdate();

            ResultSet resultSet = addStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);

                addDescriptionStatement.setString(1, language.toString().toLowerCase());
                addDescriptionStatement.setInt(2, id);
                addDescriptionStatement.setString(3, manufacturer.getName());
                addDescriptionStatement.setString(4, manufacturer.getAddress());
                addDescriptionStatement.executeUpdate();
            } else {
                throw new DAOException("Auto increment failed");
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Manufacturer> getManufacturerList(Language language, int number, int offset) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_MANUFACTURERS)) {
            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, number);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            ManufacturerBuilder manufacturerBuilder = new ManufacturerBuilderImpl(resultSet);
            while (resultSet.next()) {
                manufacturerBuilder.buildFullManufacturer();
                manufacturers.add(manufacturerBuilder.getManufacturer());
            }
            return manufacturers;
        } catch (SQLException e) {
            logger.error("Not able to get manufacturer list", e);
            throw new DAOException("An error has occurred in attempt of getting manufacturer list", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Manufacturer getManufacturer(int manufacturerId, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_MANUFACTURER)) {
            statement.setInt(1, manufacturerId);
            statement.setString(2, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            ManufacturerBuilder manufacturerBuilder = new ManufacturerBuilderImpl(resultSet);
            if (!resultSet.next()) {
                return null;
            }
            manufacturerBuilder.buildFullManufacturer();
            return manufacturerBuilder.getManufacturer();
        } catch (SQLException e) {
            logger.error("Not able to get manufacturer with id = " + manufacturerId, e);
            throw new DAOException("An error has occurred in attempt of getting manufacturer", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int getManufacturerCount(Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNT)) {
            statement.setString(1, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            logger.error("Not able to count manufacturers", e);
            throw new DAOException("An error has occurred in attempt of counting manufacturers in database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Manufacturer> findManufacturer(String content, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_MANUFACTURER)) {
            List<Manufacturer> manufacturerList = new ArrayList<>();

            statement.setString(1, "%" + content + "%");
            statement.setString(2, language.toString().toLowerCase());

            ResultSet set = statement.executeQuery();
            ManufacturerBuilder builder = new ManufacturerBuilderImpl(set);

            while (set.next()) {
                builder.createManufacturer();
                builder.buildCountry();
                builder.buildManufacturerDescription();
                builder.buildManufacturerInfo();
                manufacturerList.add(builder.getManufacturer());
            }
            return manufacturerList;
        } catch (SQLException e) {
            logger.error("Not able to find manufacturer", e);
            throw new DAOException("An error has occurred in attempt of finding manufacturer in database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
