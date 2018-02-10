package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;

import java.util.Map;

public interface LanguageDAO {
    Map<String, Language> getLanguageMap(Language language) throws DAOException;
}
