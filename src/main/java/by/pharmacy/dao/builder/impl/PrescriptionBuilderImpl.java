package by.pharmacy.dao.builder.impl;

import by.pharmacy.dao.builder.PrescriptionBuilder;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Prescription;
import by.pharmacy.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionBuilderImpl implements PrescriptionBuilder {

    protected Prescription prescription;
    protected ResultSet resultSet;


    public PrescriptionBuilderImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }


    @Override
    public void createPrescription() {
        this.prescription = new Prescription();
    }

    @Override
    public void buildClient() throws SQLException {
        User client = new User();
        client.setLogin(resultSet.getString("client"));
        this.prescription.setClient(client);
    }

    @Override
    public void buildDoctor() throws SQLException {
        User doctor = new User();
        doctor.setLogin(resultSet.getString("doctor"));
        doctor.setName(resultSet.getString("user.name"));
        doctor.setSurname(resultSet.getString("user.surname"));
        doctor.setEmail(resultSet.getString("user.email"));
        doctor.setPhoneNumber(resultSet.getString("user.phone"));
        this.prescription.setDoctor(doctor);
    }

    @Override
    public void buildDrug() throws SQLException {
        Drug drug = new Drug();
        drug.setId(resultSet.getInt("drug.id"));
        drug.setName(resultSet.getString("drug_translate.name"));
        this.prescription.setDrug(drug);
    }

    @Override
    public void buildPrescriptionInfo() throws SQLException {
        this.prescription.setNumber(resultSet.getInt("prescription.number"));
        this.prescription.setStartDate(resultSet.getDate("start_date"));
        this.prescription.setEndDate(resultSet.getDate("end_date"));
        this.prescription.setId(resultSet.getInt("prescription.id"));
    }

    @Override
    public void buildFullPrescription() throws SQLException {
        this.createPrescription();
        this.buildClient();
        this.buildDoctor();
        this.buildDrug();
        this.buildPrescriptionInfo();
    }


    @Override
    public Prescription getPrescription() {
        return this.prescription;
    }
}
