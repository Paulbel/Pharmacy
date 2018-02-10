package by.pharmacy.dao.impl;

import by.pharmacy.dao.PrescriptionDAO;
import by.pharmacy.dao.builder.PrescriptionBuilder;
import by.pharmacy.dao.builder.impl.PrescriptionBuilderImpl;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Prescription;
import by.pharmacy.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private final static String ADD_PRESCRIPTION = "INSERT INTO prescription (client, doctor, drug, start_date, end_date, number) " +
            "VALUES (?,?,?,?,?,?)";
    private final static String GET_PRESCRIPTION_BY_ID = "SELECT prescription.id,client,doctor,drug,start_date,end_date,drug.id, prescription.number, drug_translate.name " +
            "FROM prescription " +
            "INNER JOIN drug ON prescription.drug = drug.id " +
            "INNER JOIN drug_translate ON drug.id = drug_translate.drug_id " +
            "WHERE prescription.id = ? " +
            "AND drug_translate.lang_name = ?";
    private final static String GET_PRESCRIPTION_OF_USER = "SELECT prescription.id,client,doctor,drug,start_date,end_date,drug.id, prescription.number, drug_translate.name " +
            "FROM prescription " +
            "INNER JOIN drug ON prescription.drug = drug.id " +
            "INNER JOIN drug_translate ON drug.id = drug_translate.drug_id " +
            "WHERE drug_translate.lang_name = ? " +
            "AND ? = ?";
    private final static String GET_PRESCRIPTION_FOR_DRUG = "SELECT prescription.id,client,doctor,drug,start_date,end_date,drug.id, prescription.number " +
            "FROM prescription INNER JOIN drug ON prescription.drug = drug.id " +
            "WHERE  drug.id = ? " +
            "AND client = ?";

    @Override
    public void addPrescription(String doctorLogin, String clientLogin, Drug drug, int dayCount) throws DAOException {
        long currentDateMillis = System.currentTimeMillis();
        Timestamp startDate = new Timestamp(currentDateMillis);
        Timestamp endDate = new Timestamp(TimeUnit.DAYS.toMillis(dayCount) + currentDateMillis);

        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRESCRIPTION)) {
            statement.setString(1, clientLogin);
            statement.setString(2, doctorLogin);
            statement.setInt(3, drug.getId());
            statement.setTimestamp(4, startDate);
            statement.setTimestamp(5, endDate);
            statement.setInt(6, drug.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add prescription", e);
            throw new DAOException("An error has occurred in attempt of adding prescription to database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Prescription getPrescription(int prescriptionId, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRESCRIPTION_BY_ID)) {

            statement.setInt(1, prescriptionId);
            statement.setString(2, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilderImpl(resultSet);
            prescriptionBuilder.buildFullPrescription();
            return prescriptionBuilder.getPrescription();
        } catch (SQLException e) {
            logger.error("Not able to get prescription with id = " + prescriptionId + ", check if language supported", e);
            throw new DAOException("An error has occurred in attempt of getting prescription from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Prescription> getPrescriptionList(User user, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            List<Prescription> prescriptionList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_PRESCRIPTION_OF_USER);

            statement.setString(1, language.toString().toLowerCase());
            statement.setString(2, user.getRole().toString().toLowerCase());
            statement.setString(3, user.getLogin());

            ResultSet resultSet = statement.executeQuery();

            PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilderImpl(resultSet);
            while (resultSet.next()) {
                prescriptionBuilder.buildFullPrescription();
                prescriptionList.add(prescriptionBuilder.getPrescription());
            }
            return prescriptionList;
        } catch (SQLException e) {
            logger.error("Not able to get prescription list", e);
            throw new DAOException("An error has occurred in attempt of getting prescriptions from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Prescription getPrescriptionForDrug(int drugId, String clientLogin) throws DAOException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(GET_PRESCRIPTION_FOR_DRUG)) {
            statement.setInt(1, drugId);
            statement.setString(2, clientLogin);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilderImpl(resultSet);
            prescriptionBuilder.createPrescription();
            prescriptionBuilder.buildPrescriptionInfo();
            return prescriptionBuilder.getPrescription();
        } catch (SQLException e) {
            logger.error("Not able to get prescription", e);
            throw new DAOException("An error has occurred in attempt of getting specific prescription from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
