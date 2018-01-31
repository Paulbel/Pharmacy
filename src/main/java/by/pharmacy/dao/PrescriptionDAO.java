package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.User;

public interface PrescriptionDAO {
    void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws DAOException;

}
