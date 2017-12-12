package by.pharmacy.service;

import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface PharmacistService {
    void addDrug(int drugId);

    void removeDrug(int drugId);

    List<Drug> getAllDrugs(Language language) throws ServiceException;
}
