package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;

import java.util.Map;

/**
 * Data access object interface, which is intended to work with {@link Language} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface LanguageDAO {
    /**
     * Returns map of {@link Language} with locale language names as a key.
     *
     * @param language object of class {@link Language} which represents locale of return value.
     * @return object of class {@link Map} which has language name as a key and {@link Language} as value.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Map<String, Language> getLanguageMap(Language language) throws DAOException;
}
