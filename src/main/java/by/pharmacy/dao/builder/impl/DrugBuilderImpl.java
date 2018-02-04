package by.pharmacy.dao.builder.impl;

import by.pharmacy.dao.builder.DrugBuilder;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugBuilderImpl implements DrugBuilder {
    private Drug drug;
    private ResultSet resultSet;

    public DrugBuilderImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public DrugBuilderImpl create() {
        this.drug = new Drug();
        return this;
    }

    public DrugBuilderImpl buildDrugInfo() throws SQLException {
        int id = resultSet.getInt("drug.id");
        int number = resultSet.getInt("drug.number");
        int amount = resultSet.getInt("drug.amount");
        int dosage = resultSet.getInt("drug.dosage");
        boolean needPrescription = resultSet.getBoolean("drug.need_prescription");
        double price = resultSet.getDouble("drug.price");

        drug.setId(id);
        drug.setNumber(number);
        drug.setAmount(amount);
        drug.setDosage(dosage);
        drug.setPrice(price);
        drug.setNeedPrescription(needPrescription);

        return this;
    }

    public DrugBuilderImpl buildDrugDescription() throws SQLException {
        String name = resultSet.getString("drug_translate.name");
        String composition = resultSet.getString("drug_translate.composition");
        String description = resultSet.getString("drug_translate.description");

        drug.setName(name);
        drug.setComposition(composition);
        drug.setDescription(description);

        return this;
    }

    public DrugBuilderImpl buildDrugManufacturer() throws SQLException {
        int manufacturerId = resultSet.getInt("manufacturer.id");
        String phoneNumber = resultSet.getString("manufacturer.phone_number");
        String manufacturerName = resultSet.getString("manufacturer_translate.name");
        String address = resultSet.getString("manufacturer_translate.address");
        String email = resultSet.getString("manufacturer.email");
        String countryName = resultSet.getString("country_translate.name");
        String countryCode = resultSet.getString("manufacturer.country_code");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setAddress(address);
        manufacturer.setName(manufacturerName);
        manufacturer.setPhoneNumber(phoneNumber);
        manufacturer.setEmail(email);

        Country country = new Country();
        country.setName(countryName);
        country.setCode(countryCode);

        manufacturer.setCountry(country);

        drug.setManufacturer(manufacturer);

        return this;
    }

    public Drug get() {
        return drug;
    }
}
