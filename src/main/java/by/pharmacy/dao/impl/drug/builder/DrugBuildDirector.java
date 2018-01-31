package by.pharmacy.dao.impl.drug.builder;

import by.pharmacy.dao.impl.drug.builder.drugBuilder.DrugBuilder;

import java.sql.SQLException;

public class DrugBuildDirector {
    private DrugBuilder drugBuilder;
    public DrugBuildDirector(DrugBuilder drugBuilder){
        this.drugBuilder = drugBuilder;
    }
    public void build() throws SQLException {
        drugBuilder.buildDrug();
        drugBuilder.buildDrugInfo();
        drugBuilder.buildDrugDescription();
        drugBuilder.buildDrugManufacturer();
    }
}
