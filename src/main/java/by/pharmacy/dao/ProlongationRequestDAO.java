package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.ProlongationRequest;
import by.pharmacy.entity.ProlongationRequestStatus;

public interface ProlongationRequestDAO {
    void addProlongationRequest(ProlongationRequest request) throws DAOException;

    void changeProlongationRequestStatus(long requestId, ProlongationRequestStatus status) throws DAOException;
}
