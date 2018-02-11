package by.pharmacy.dao;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.ProlongationRequest;
import by.pharmacy.entity.ProlongationRequestStatus;


/**
 * Data access object interface, which is intended to work with {@link ProlongationRequest} objects.
 *
 * @author Pavel Sinelnikau
 * @since 1.0.0
 */
public interface ProlongationRequestDAO {


    /**
     * Creates prolongation request in data source.
     *
     * @param request has data.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void addProlongationRequest(ProlongationRequest request) throws DAOException;

    /**
     * Changes status of prolongation request.
     *
     * @param requestId id of prolongation request.
     * @param status    new status of prolongation request.
     * @throws DAOException if something is wrong with getting information from data source.
     */
    void changeProlongationRequestStatus(long requestId, ProlongationRequestStatus status) throws DAOException;
}
