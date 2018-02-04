package by.pharmacy.dao.builder.impl;

import by.pharmacy.dao.builder.ManufacturerBuilder;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerBuilderImpl implements ManufacturerBuilder {
    protected Manufacturer manufacturer;
    protected ResultSet resultSet;

    public ManufacturerBuilderImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void createManufacturer() {
        this.manufacturer = new Manufacturer();
    }

    @Override
    public void buildManufacturerInfo() throws SQLException {
        int id = resultSet.getInt("manufacturer.id");
        String email = resultSet.getString("manufacturer.email");
        String phoneNumber = resultSet.getString("manufacturer.phone_number");

        manufacturer.setId(id);

        manufacturer.setEmail(email);
        manufacturer.setPhoneNumber(phoneNumber);
    }

    @Override
    public void buildManufacturerDescription() throws SQLException {
        String name = resultSet.getString("manufacturer_translate.name");
        String address = resultSet.getString("manufacturer_translate.address");

        manufacturer.setAddress(address);
        manufacturer.setName(name);

    }

    @Override
    public void buildCountry() throws SQLException {
        String countryName = resultSet.getString("country_translate.name");
        String countryCode = resultSet.getString("country_translate.country_code");
        Country country = new Country();
        country.setName(countryName);
        country.setCode(countryCode);
        manufacturer.setCountry(country);
    }
}

