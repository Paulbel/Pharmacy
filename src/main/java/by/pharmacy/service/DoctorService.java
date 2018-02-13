package by.pharmacy.service;

import by.pharmacy.entity.Drug;
import by.pharmacy.entity.User;
import by.pharmacy.service.exception.ServiceException;

import java.util.List;

public interface DoctorService {
    void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws ServiceException;

    List<User> getClientList() throws ServiceException;

    void changeProlongationRequestStatus(String idString, String doctorLogin, String statusString) throws ServiceException;

    User getClient(String doctorLogin, String clientLogin) throws ServiceException;

    List<User> findUser(String namePart) throws ServiceException;
}
