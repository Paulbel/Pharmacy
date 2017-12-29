package by.pharmacy.service;

import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface PharmacistService {
    void addDrug(Drug drug, Language language) throws ServiceException;

    void removeDrug(int drugId);

    List<Drug> getDrugs(Language language, int number,int offset) throws ServiceException;

    int getDrugNumber() throws ServiceException;

    void addDrugDescription(String pharmacistLogin, Drug drug, Language language) throws ServiceException;
}
