package by.pharmacy.service;

import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface DoctorService {
    void addPrescription(String doctorLogin, String clientLogin, int drugId) throws ServiceException;

    List<User> getClientList() throws ServiceException;
}
