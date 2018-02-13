package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Prescription;

import java.util.List;


/**
 * Data access object interface, which is intended to work with {@link Prescription} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface PrescriptionDAO {
    /**
     * Creates prescription entry in data source.
     *
     * @param doctorLogin login of doctor, who created prescription.
     * @param clientLogin login of prescription owner.
     * @param drug        drug entity which has drug id and number of drugs for prescription.
     * @param dayCount    number of days during which prescription works.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws DAOException;

    /**
     * Returns prescription with id = prescriptionId param, if it exists in data source.
     *
     * @param prescriptionId id of prescription which is being found.
     * @param language       object of class {@link Language} which represents locale of return value.
     * @return object of class {@link Prescription} or null if data source doesn't have specific entry.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Prescription getPrescription(int prescriptionId, Language language) throws DAOException;

    /**
     * Returns prescription list of specified client from data source.
     *
     * @param clientLogin client login.
     * @param language    object of class {@link Language} which represents locale of return value.
     * @return object of class {@link List} which has all {@link Prescription} of specified user param.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    List<Prescription> getPrescriptionList(String clientLogin, Language language) throws DAOException;

    /**
     * Returns prescription list for specific drug from data source.
     *
     * @param drugId      id of drug.
     * @param clientLogin login of user.
     * @return object of class {@link Prescription}.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    Prescription getPrescriptionForDrug(int drugId, String clientLogin) throws DAOException;


    /**
     * Changes number of drugs in prescription in data source.
     *
     * @param prescriptionId id of prescription.
     * @param number         number of drugs that should be set.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void changePrescriptionDrugNumber(int prescriptionId, int number) throws DAOException;

    /**
     * Removes prescription with id param from data source.
     *
     * @param prescriptionId id of prescription.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void removePrescription(int prescriptionId) throws DAOException;
}
