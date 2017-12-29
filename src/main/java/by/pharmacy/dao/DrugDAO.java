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

    List<Drug> findDrugs(String name, Language language, int number, int offset) throws DAOException;

    int getDrugNumber() throws DAOException;

    void removeDrug(int drugId) throws DAOException;
}
