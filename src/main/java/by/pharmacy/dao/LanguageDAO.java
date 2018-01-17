package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;

import java.util.Map;

public interface LanguageDAO {
    Map<String, Language> getLanguages(Language language) throws DAOException;
}
