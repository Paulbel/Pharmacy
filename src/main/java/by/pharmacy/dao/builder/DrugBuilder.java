package by.pharmacy.dao.builder;

import by.pharmacy.dao.builder.impl.DrugBuilderImpl;
import by.pharmacy.entity.Drug;

import java.sql.SQLException;

public interface DrugBuilder {

    DrugBuilderImpl create();

    DrugBuilderImpl buildDrugInfo() throws SQLException;

    DrugBuilderImpl buildDrugDescription() throws SQLException;

    DrugBuilderImpl buildDrugManufacturer() throws SQLException;

    Drug get();
}
