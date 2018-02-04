package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Prescription;
import by.pharmacy.entity.User;

import java.util.List;

public interface PrescriptionDAO {
    void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws DAOException;

    Prescription getPrescription(int prescriptionId, Language language) throws DAOException;

    List<Prescription> getPrescriptionList(User user, Language language) throws DAOException;

    Prescription getPrescriptionForDrug(int drugId, String clientLogin) throws DAOException;
}
