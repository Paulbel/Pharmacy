package by.pharmacy.dao.impl;

import by.pharmacy.dao.ManufacturerDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;

import java.sql.*;
import java.util.List;

import static by.pharmacy.dao.DAOConstant.*;
import static by.pharmacy.dao.impl.SQLManufacturerDAOConstant.*;

public class SQLManufacturerDAO implements ManufacturerDAO {
    @Override
    public void addManufacturer(Manufacturer manufacturer, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(FIND_LANGUAGE_CODE_BY_NAME);
            String languageName = language.name().toLowerCase();
            statement.setString(FIND_LANGUAGE_CODE_BY_NAME_NAME_INDEX, languageName);
            ResultSet set = statement.executeQuery();
            set.next();
            String languageCode = set.getString(LANGUAGE_TABLE_CODE);
            statement = connection.prepareStatement(GET_COUNTRY_CODE);

            statement.setString(GET_COUNTRY_CODE_LANGUAGE_CODE_INDEX, languageCode);
            statement.setString(GET_COUNTRY_CODE_NAME_INDEX, manufacturer.getCountry());

            set = statement.executeQuery();

            set.next();

            String countryCode =  set.getString(COUNTRY_TRANSLATE_TABLE_COUNTRY_CODE);

            PreparedStatement stat = connection.prepareStatement(ADD_MANUFACTURER, Statement.RETURN_GENERATED_KEYS);

            stat.setString(ADD_MANUFACTURER_COUNTRY_CODE_INDEX, countryCode);
            stat.setString(ADD_MANUFACTURER_PHONE_NUMBER_INDEX, manufacturer.getPhoneNumber());
            stat.setString(ADD_MANUFACTURER_EMAIL_INDEX, manufacturer.getEmail());

            stat.executeUpdate();

            set = stat.getGeneratedKeys();
            set.next();
            int id = set.getInt("GENERATED_KEY");//I dont know why not
            statement = connection.prepareStatement(ADD_MANUFACTURER_TRANSLATE);
            statement.setInt(ADD_MANUFACTURER_TRANSLATE_MANUFACTURER_ID_INDEX, id);
            statement.setString(ADD_MANUFACTURER_TRANSLATE_LANGUAGE_CODE_INDEX, languageCode);
            statement.setString(ADD_MANUFACTURER_TRANSLATE_NAME_INDEX, manufacturer.getName());
            statement.setString(ADD_MANUFACTURER_TRANSLATE_ADDRESS_INDEX, manufacturer.getAddress());
            statement.executeUpdate();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Manufacturer> getManufacturers(int number, int offset) throws DAOException {
        return null;
    }
}
