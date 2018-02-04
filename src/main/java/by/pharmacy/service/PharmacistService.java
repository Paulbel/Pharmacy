package by.pharmacy.service;

import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.service.exception.ServiceException;

public interface PharmacistService {
    void addDrug(Drug drug, Language language) throws ServiceException;

    void addDrugDescription(String pharmacistLogin, Drug drug, Language language) throws ServiceException;

    void changeDrugDescription(Drug drug, Language language) throws ServiceException;

    void changeDrugInfo(Drug drug) throws ServiceException;

    void addManufacturer(Manufacturer manufacturer, Language language) throws ServiceException;

    void removeDrug(int drugId, String doctorLogin) throws ServiceException;
}
