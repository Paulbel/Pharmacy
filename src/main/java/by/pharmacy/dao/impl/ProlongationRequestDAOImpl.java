package by.pharmacy.dao.impl;

import by.pharmacy.dao.ProlongationRequestDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.ProlongationRequest;
import by.pharmacy.entity.ProlongationRequestStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProlongationRequestDAOImpl implements ProlongationRequestDAO {
    private final static String ADD_REQUEST = "INSERT INTO prolongation_request (prescription_id, day_count) VALUES (?,?)";
    private final static String CHANGE_STATUS = "UPDATE prolongation_request SET status = ? WHERE id = ?";

    @Override
    public void addProlongationRequest(ProlongationRequest request) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_REQUEST)) {

            statement.setInt(1, request.getPrescription().getId());
            statement.setInt(2, request.getTerm());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("happened", e);
        }
    }

    @Override
    public void changeProlongationRequestStatus(long requestId, ProlongationRequestStatus status) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS)) {
            statement.setString(1, status.toString().toLowerCase());
            statement.setLong(2, requestId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("happened", e);
        }
    }
}
