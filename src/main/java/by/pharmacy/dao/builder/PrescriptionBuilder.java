package by.pharmacy.dao.builder;

import by.pharmacy.entity.Prescription;

import java.sql.SQLException;

public interface PrescriptionBuilder {
    void createPrescription();

    void buildClient() throws SQLException;

    void buildDoctor() throws SQLException;

    void buildDrug() throws SQLException;

    void buildPrescriptionInfo() throws SQLException;

    void buildFullPrescription() throws SQLException;

    Prescription getPrescription();
}
