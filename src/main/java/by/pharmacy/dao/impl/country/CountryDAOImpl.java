package by.pharmacy.dao.impl.country;

import by.pharmacy.dao.CountryDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private final static String GET_COUNTRY_LIST = "SELECT country_translate.country_code, name FROM country_translate WHERE lan_name = ?";


    @Override
    public List<Country> getCountryList(Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_LIST)) {
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
            throw new DAOException("Can't add description", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
