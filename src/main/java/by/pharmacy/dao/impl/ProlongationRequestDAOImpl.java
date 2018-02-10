package by.pharmacy.dao.impl;

import by.pharmacy.dao.ProlongationRequestDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.ProlongationRequest;
import by.pharmacy.entity.ProlongationRequestStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProlongationRequestDAOImpl implements ProlongationRequestDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private final static String ADD_REQUEST = "INSERT INTO prolongation_request (prescription_id, day_count) VALUES (?,?)";
    private final static String CHANGE_STATUS = "UPDATE prolongation_request SET status = ? WHERE id = ?";

    @Override
    public void addProlongationRequest(ProlongationRequest request) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_REQUEST)) {

            statement.setInt(1, request.getPrescription().getId());
            statement.setInt(2, request.getTerm());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add prolongation request", e);
            throw new DAOException("An error has occurred in attempt of adding prolongation request to database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeProlongationRequestStatus(long requestId, ProlongationRequestStatus status) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS)) {
            statement.setString(1, status.toString().toLowerCase());
            statement.setLong(2, requestId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to change prolongation request status", e);
            throw new DAOException("An error has occurred in attempt of changing prolongation request status", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
