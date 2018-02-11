package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.DrugCriteria;
import by.pharmacy.entity.Language;

import java.util.List;


/**
 * Data access object interface, which is intended to work with {@link Drug} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface DrugDAO {
    /**
     * Returns object of {@link List} with {@link Drug} from some data source.
     *
     * @param language   object of class {@link Language} which represents locale of return value.
     * @param number     max number of entries which return value has.
     * @param offset     number of entries from the start point of data source, which will be skipped.
     * @param orderField object of class {@link DrugCriteria} which represents sorting order.
     * @return list of elements which are represented in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Drug> getDrugList(Language language, int number, int offset, DrugCriteria orderField) throws DAOException;

    /**
     * Adds object of class {@link Drug} to some data source.
     *
     * @param drug     entity which should be added to data source.
     * @param language locale of info of entity which is being added.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addDrug(Drug drug, Language language) throws DAOException;

    /**
     * Adds description(locale info) of object of class {@link Drug} to some data source.
     *
     * @param drug     entity which description should be added to data source.
     * @param language locale of description which is being added.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addDrugDescription(Drug drug, Language language) throws DAOException;

    /**
     * Returns object of java.util.List with {@link Drug} which names includes name param from some data source.
     *
     * @param name       name or part of name of drug which should be found.
     * @param language   locale of drug info which is being found.
     * @param orderField object of class {@link DrugCriteria} which represents sorting order.
     * @return list of elements in datasource ordered by orderField param.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Drug> findDrugByName(String name, Language language, DrugCriteria orderField) throws DAOException;

    /**
     * Returns number of specific entries in data source.
     *
     * @param language locale of drug info which is being found.
     * @return number of entries.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    int getDrugCount(Language language) throws DAOException;


    /**
     * Removes entry from data source.
     *
     * @param drugId id of drug which should be removed from data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void removeDrug(int drugId) throws DAOException;

    /**
     * Returns object of {@link Drug} with id = drugId param.
     *
     * @param drugId   id of drug entry in data source.
     * @param language locale of drug which is being received.
     * @return object of {@link Drug} if exists and null if not.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Drug getDrug(int drugId, Language language) throws DAOException;

    /**
     * Returns object of {@link Drug} without any description(locale info).
     *
     * @param drugId id of drug.
     * @return drug information if entry exists and null if not.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Drug getDrugInfo(int drugId) throws DAOException;

    /**
     * Returns List of drugs which are produced by specific manufacturer.
     *
     * @param manufacturerId id of manufacturer of drugs.
     * @param language       locale of info of entity which is being found.
     * @param number         number of entries from the start point of data source, which will be skipped.
     * @param offset         number of entries from the start point of data source, which will be skipped.
     * @param orderField     object of class {@link DrugCriteria} which represents sorting order.
     * @return list of drugs which have specified manufacturer, represented in data source.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Drug> findDrugsByManufacturer(int manufacturerId, Language language, int number, int offset, DrugCriteria orderField) throws DAOException;

    /**
     * Changes locale info of specific drug in data source.
     *
     * @param drug     has locale info.
     * @param language locale of info of entity which is being added.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void changeDrugDescription(Drug drug, Language language) throws DAOException;

    /**
     * Changes not locale info of specific drug in data source.
     *
     * @param drug has info.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void changeDrugInfo(Drug drug) throws DAOException;

}
