package by.pharmacy.dao.builder;

import by.pharmacy.entity.Drug;

import java.sql.SQLException;

public interface DrugBuilder {

    void create();

    void buildDrugInfo() throws SQLException;

    void buildDrugDescription() throws SQLException;

    void buildDrugManufacturer() throws SQLException;

    void buildFullDrug() throws SQLException;

    Drug get();
}
