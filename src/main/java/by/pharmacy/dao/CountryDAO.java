package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Language;

import java.util.List;

public interface CountryDAO {
    List<Country> getCountryList(Language language) throws DAOException;
}
