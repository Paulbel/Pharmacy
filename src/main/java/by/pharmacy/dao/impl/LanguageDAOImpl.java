package by.pharmacy.dao.impl;

import by.pharmacy.dao.LanguageDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LanguageDAOImpl implements LanguageDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(LanguageDAOImpl.class);
    private final static String GET_LANGUAGES = "SELECT language_to_translate,language_name, name FROM language_translate WHERE language_name = ?;";

    @Override
    public Map<String, Language> getLanguageMap(Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ResultSet set;
        try (PreparedStatement statement = connection.prepareStatement(GET_LANGUAGES)) {
            statement.setString(1, language.toString());

            set = statement.executeQuery();

            Map<String, Language> languageStringMap = new HashMap<>();
            while (set.next()) {
                String languageToTranslateName = set.getString("language_to_translate");
                String languageOrigin = set.getString("name");
                languageStringMap.put(languageOrigin, Language.valueOf(languageToTranslateName.toUpperCase()));
            }
            return languageStringMap;
        } catch (SQLException e) {
            logger.error("Not able to get languages from database", e);
            throw new DAOException("An error has occurred in attempt of getting map of languages", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
