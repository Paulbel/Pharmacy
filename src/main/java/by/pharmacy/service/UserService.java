package by.pharmacy.service;

import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    void signUp(User user, String password) throws ServiceException;

    List<Drug> getDrugs(Language language, int number, int offset) throws ServiceException;

    List<Drug> findDrug(String name, Language language) throws ServiceException;

    int getDrugNumber() throws ServiceException;

    Map<String, Language> getLanguages(Language language) throws ServiceException;

    List<Manufacturer> getManufacturers(Language language, int number, int offset) throws ServiceException;

    int getManufacturerNumber() throws ServiceException;

    Manufacturer getManufacturer(int id, Language language) throws ServiceException;


    Drug getDrug(int drugId, Language language) throws ServiceException;
}
