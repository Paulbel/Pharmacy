package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;

import java.util.List;


/**
 * Data access object interface, which is intended to work with {@link Manufacturer} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface ManufacturerDAO {
    /**
     * Adds manufacturer do data source.
     *
     * @param manufacturer has information to add.
     * @param language     object of class {@link Language} which represents locale of return value.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addManufacturer(Manufacturer manufacturer, Language language) throws DAOException;

    /**
     * Returns list of manufacturers
     *
     * @param language object of class {@link Language} which represents locale of return value.
     * @param number   max number of entries which return value has.
     * @param offset   number of entries from the start point of data source, which will be skipped.
     * @return object of class {@link List} which has all {@link Manufacturer} objects that were found.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Manufacturer> getManufacturerList(Language language, int number, int offset) throws DAOException;

    /**
     * Returns manufacturer with specific id.
     *
     * @param manufacturerId id of manufacturer which should be found.
     * @param language       object of class {@link Language} which represents locale of return value.
     * @return object of class {@link Manufacturer} or null if there is no such entry in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Manufacturer getManufacturer(int manufacturerId, Language language) throws DAOException;

    /**
     * Returns number of manufacturers in data source with specific locale.
     *
     * @param language object of class {@link Language} which represents locale of return value.
     * @return number of manufacturers in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    int getManufacturerCount(Language language) throws DAOException;

    /**
     * Returns manufacturer list with names which includes content param in their names.
     *
     * @param content  part of name or a whole name.
     * @param language object of class {@link Language} which represents locale of return value.
     * @return object of class {@link List} which include content .
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Manufacturer> findManufacturer(String content, Language language) throws DAOException;
}
