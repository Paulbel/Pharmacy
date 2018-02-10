package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.DrugCriteria;
import by.pharmacy.entity.Language;

import java.util.List;

public interface DrugDAO {
    List<Drug> getDrugList(Language language, int number, int offset, DrugCriteria orderField) throws DAOException;

    void addDrug(Drug drug, Language language) throws DAOException;

    void addDrugDescription(Drug drug, Language language) throws DAOException;

    List<Drug> findDrugByName(String name, Language language, DrugCriteria orderField) throws DAOException;

    int getDrugCount(Language language) throws DAOException;

    void removeDrug(int drugId) throws DAOException;

    Drug getDrug(int drugId, Language language) throws DAOException;

    Drug getDrugInfo(int drugId) throws DAOException;

    List<Drug> findDrugsByManufacturer(int manufacturerId, Language language, int number, int offset, DrugCriteria orderField) throws DAOException;

    void changeDrugDescription(Drug drug, Language language) throws DAOException;

    void changeDrugInfo(Drug drug) throws DAOException;

}
