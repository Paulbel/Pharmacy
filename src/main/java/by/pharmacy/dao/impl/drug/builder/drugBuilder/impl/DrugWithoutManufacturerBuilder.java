package by.pharmacy.dao.impl.drug.builder.drugBuilder.impl;

import by.pharmacy.dao.impl.drug.builder.drugBuilder.DrugBuilder;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugWithoutManufacturerBuilder extends DrugFullBuilder{

    public DrugWithoutManufacturerBuilder(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public void buildDrugManufacturer() throws SQLException {

    }
}
