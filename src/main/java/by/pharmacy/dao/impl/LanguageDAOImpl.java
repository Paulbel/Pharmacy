package by.pharmacy.dao.impl;

import by.pharmacy.dao.LanguageDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LanguageDAOImpl implements LanguageDAO {

private final static String GET_LANGUAGES = "SELECT language_to_translate,language_name, name FROM language_translate WHERE language_name = ?;";
    @Override
    public Map<String, Language> getLanguages(Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet set;
        try (PreparedStatement statement = connection.prepareStatement(GET_LANGUAGES)) {
            statement.setString(1, language.toString());

            set = statement.executeQuery();

            Map<String,Language> languageStringMap = new HashMap<>();
            while (set.next()) {
                String languageToTranslateName = set.getString("language_to_translate");
                String languageOrigin = set.getString("name");
                languageStringMap.put(languageOrigin, Language.valueOf(languageToTranslateName.toUpperCase()));
            }
            return languageStringMap;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
