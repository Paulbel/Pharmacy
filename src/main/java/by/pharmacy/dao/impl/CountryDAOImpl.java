package by.pharmacy.dao.impl;

import by.pharmacy.dao.CountryDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Language;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private final static String GET_COUNTRY_LIST_QUERY = "SELECT country_translate.country_code, name FROM country_translate WHERE lan_name = ?";

    @Override
    public List<Country> getCountryList(Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_LIST_QUERY)) {
            statement.setString(1, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            List<Country> countryList = new ArrayList<>();
            while (resultSet.next()) {
                Country country = new Country();

                String code = resultSet.getString("country_translate.country_code");
                String name = resultSet.getString("country_translate.name");

                country.setCode(code);
                country.setName(name);

                countryList.add(country);
            }
            return countryList;
        } catch (SQLException e) {
            logger.error("Not able to get country list from database", e);
            throw new DAOException("An error has occurred in attempt of getting country list from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
