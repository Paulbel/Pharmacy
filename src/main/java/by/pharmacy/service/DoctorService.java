package by.pharmacy.service;

import by.pharmacy.service.exception.ServiceException;

public interface DoctorService {
    void addPrescription(String doctorLogin, String clientLogin, int drugId) throws ServiceException;
}
