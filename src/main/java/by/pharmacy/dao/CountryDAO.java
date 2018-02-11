package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Language;

import java.util.List;

/**
 * Data access object interface, which is intended to work with {@link Country} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface CountryDAO {
    /**
     * Returns object of java.util.List with by.pharmacy.entity.Country from some data source.
     *
     * @param language object of class by.pharmacy.entity.Language which represents locale of return value.
     * @return {@link List} which is created and formed in method.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Country> getCountryList(Language language) throws DAOException;
}
