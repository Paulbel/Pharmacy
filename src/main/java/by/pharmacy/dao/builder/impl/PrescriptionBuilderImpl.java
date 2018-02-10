package by.pharmacy.dao.builder.impl;

import by.pharmacy.dao.builder.PrescriptionBuilder;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Prescription;
import by.pharmacy.entity.User;

import java.sql.Date;
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
        String clientLogin = resultSet.getString("client");
        User client = new User();
        client.setLogin(clientLogin);
        this.prescription.setClient(client);
    }

    @Override
    public void buildDoctor() throws SQLException {
        String doctorLogin = resultSet.getString("doctor");
        User doctor = new User();
        doctor.setLogin(doctorLogin);
        this.prescription.setDoctor(doctor);
    }

    @Override
    public void buildDrug() throws SQLException {

        int number = resultSet.getInt("prescription.number");
        int drugId = resultSet.getInt("drug.id");
        String drugName = resultSet.getString("drug_translate.name");
        Drug drug = new Drug();
        drug.setNumber(number);
        drug.setId(drugId);
        drug.setName(drugName);
        this.prescription.setDrug(drug);
    }

    @Override
    public void buildPrescriptionInfo() throws SQLException {
        int prescriptionId = resultSet.getInt("prescription.id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");
        this.prescription.setStartDate(startDate);
        this.prescription.setEndDate(endDate);
        this.prescription.setId(prescriptionId);
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
