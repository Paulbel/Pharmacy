package by.pharmacy.service;

import by.pharmacy.entity.*;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    void signUp(User user, String password) throws ServiceException;

    List<Drug> getDrugs(Language language, int number, int offset) throws ServiceException;

    List<Drug> findDrug(String name, Language language) throws ServiceException;


    int getDrugCount(Language language) throws ServiceException;

    Map<String, Language> getLanguages(Language language) throws ServiceException;

    List<Manufacturer> getManufacturers(Language language, int number, int offset) throws ServiceException;

    int getManufacturerCount(Language language) throws ServiceException;

    Manufacturer getManufacturer(int id, Language language) throws ServiceException;

    Drug getDrug(int drugId, Language language) throws ServiceException;

    List<Country> getCountryList(Language language) throws ServiceException;
}
