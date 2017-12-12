package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;

import java.util.List;

public interface DrugDAO {
    List<Drug> getAll(Language language) throws DAOException;

    void addDrug(Drug drug, Language language) throws DAOException;

    Drug getDrug(int id, Language language) throws DAOException;
}
