package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Manufacturer;

import java.util.List;

public interface DrugDAO {
    List<Drug> getDrugs(Language language, int number, int offset) throws DAOException;

    void addDrug(Drug drug, Language language) throws DAOException;

    void addDrugDescription(Drug drug, Language language) throws DAOException;

    List<Drug> findDrugByName(String name, Language language) throws DAOException;

    int getDrugCount() throws DAOException;

    void removeDrug(int drugId) throws DAOException;

    Drug getDrug(int id, Language language) throws DAOException;

    List<Drug> findDrugsByManufacturer(int manufacturerId, Language language, int number, int offset) throws DAOException;

    void changeDrugDescription(Drug drug, Language language) throws DAOException;

    void changeDrugInfo(Drug drug) throws DAOException;
}
