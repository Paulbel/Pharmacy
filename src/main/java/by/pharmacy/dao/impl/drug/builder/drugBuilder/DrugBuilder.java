package by.pharmacy.dao.impl.drug.builder.drugBuilder;

import by.pharmacy.entity.Drug;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DrugBuilder {
    protected Drug drug;
    protected ResultSet resultSet;
    public abstract void buildDrug();
    public abstract void buildDrugInfo() throws SQLException;
    public abstract void buildDrugDescription() throws SQLException;
    public abstract void buildDrugManufacturer() throws SQLException;
    protected DrugBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    public Drug getDrug() {
        return drug;
    }
}
