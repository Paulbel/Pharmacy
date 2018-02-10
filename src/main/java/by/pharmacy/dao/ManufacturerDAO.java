package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {
    void addManufacturer(Manufacturer manufacturer, Language language) throws DAOException;

    List<Manufacturer> getManufacturerList(Language language, int number, int offset) throws DAOException;

    Manufacturer getManufacturer(int manufacturerId, Language language) throws DAOException;

    int getManufacturerCount(Language language) throws DAOException;

    List<Manufacturer> findManufacturer(String content, Language language) throws DAOException;
}
