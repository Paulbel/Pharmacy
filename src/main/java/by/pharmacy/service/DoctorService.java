package by.pharmacy.service;

import by.pharmacy.entity.Drug;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface DoctorService {
    void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws ServiceException;

    List<User> getClientList() throws ServiceException;
}
