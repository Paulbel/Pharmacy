package by.pharmacy.dao.builder;

import by.pharmacy.entity.Manufacturer;

import java.sql.SQLException;

public interface ManufacturerBuilder {
    void createManufacturer();

    void buildManufacturerInfo() throws SQLException;

    void buildManufacturerDescription() throws SQLException;

    void buildCountry() throws SQLException;

    Manufacturer getManufacturer();
}
