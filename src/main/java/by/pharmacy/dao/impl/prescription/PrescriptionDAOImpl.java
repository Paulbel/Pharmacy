package by.pharmacy.dao.impl.prescription;

import by.pharmacy.dao.PrescriptionDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class PrescriptionDAOImpl implements PrescriptionDAO {
private final static String ADD_PRESCRIPTION = "INSERT INTO prescription (client, doctor, drug, start_date, end_date, number) VALUES (?,?,?,?,?,?)";


    @Override
    public void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws DAOException {
        long currentDateMillis = System.currentTimeMillis();
        Date startDate = new Date(currentDateMillis);
        Date endDate = new Date(TimeUnit.DAYS.toMillis(dayCount)+currentDateMillis);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_PRESCRIPTION);
            statement.setString(1, clientLogin);
            statement.setString(2, doctorLogin);
            statement.setInt(3, drug.getId());
            statement.setDate(4, startDate);
            statement.setDate(5, endDate);
            statement.setInt(6, drug.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("happened",e);
        }
    }



}
