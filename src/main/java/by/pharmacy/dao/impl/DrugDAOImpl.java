package by.pharmacy.dao.impl;

import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.builder.DrugBuilder;
import by.pharmacy.dao.builder.impl.DrugBuilderImpl;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.ConnectionPoolException;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.DrugCriteria;
import by.pharmacy.entity.Language;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DrugDAOImpl implements DrugDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private final static String REMOVE_DRUG = "DELETE FROM drug WHERE id = ?";
    private final static String GET_DRUG_INFO = "SELECT" +
            "  drug.id," +
            "  drug.number," +
            "  drug.amount," +
            "  drug.dosage," +
            "  drug.need_prescription," +
            "  drug.price" +
            " FROM drug" +
            " WHERE drug.id = ? ";
    private final static String CHANGE_DRUG_INFO = "UPDATE" +
            " drug" +
            " SET drug.dosage=?," +
            " drug.amount= ?," +
            " drug.number = ?," +
            " drug.need_prescription = ?," +
            " drug.price = ?" +
            " WHERE drug.id = ?";
    private final static String CHANGE_DRUG_DESCRIPTION = "UPDATE" +
            " drug_translate" +
            " SET drug_translate.name=?," +
            " drug_translate.composition = ?," +
            " drug_translate.description = ?" +
            " WHERE drug_translate.drug_id = ? AND drug_translate.lang_name = ?";
    private final static String GET_DRUG = "SELECT" +
            "  drug.id," +
            "  drug_translate.name," +
            "  drug_translate.composition," +
            "  drug.number," +
            "  drug.amount," +
            "  drug.dosage," +
            "  drug_translate.description," +
            "  drug.need_prescription," +
            "  drug.price," +
            "  manufacturer.id," +
            "  manufacturer.phone_number," +
            "  manufacturer.email," +
            "  manufacturer.country_code," +
            "  manufacturer_translate.name," +
            "  manufacturer_translate.address," +
            "  country_translate.name" +
            " FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id" +
            " WHERE drug.id = ? AND drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND" +
            "      country_translate.lan_name = drug_translate.lang_name";
    private final static String GET_DRUG_NUMBER = "SELECT" +
            " count(drug.id) AS number FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id" +
            " WHERE drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND" +
            "      country_translate.lan_name = drug_translate.lang_name";
    private final static String GET_DRUGS_BY_MANUFACTURER = "SELECT" +
            "  drug.id," +
            "  drug_translate.name," +
            "  drug_translate.composition," +
            "  drug.number," +
            "  drug.amount," +
            "  drug.dosage," +
            "  drug_translate.description," +
            "  drug.need_prescription," +
            "  drug.price" +
            " FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            " WHERE drug.manufacturer_id = ? AND drug_translate.lang_name = ? " +
            " ORDER BY ?" +
            " LIMIT ? OFFSET ?;";
    private final static String FIND_DRUG = "SELECT" +
            "  drug.id," +
            "  drug_translate.name," +
            "  drug_translate.composition," +
            "  drug.number," +
            "  drug.amount," +
            "  drug.dosage," +
            "  drug_translate.description," +
            "  drug.need_prescription," +
            "  drug.price," +
            "  manufacturer.id," +
            "  manufacturer.phone_number," +
            "  manufacturer.email," +
            "  manufacturer_translate.name," +
            "  manufacturer_translate.address," +
            "  manufacturer.country_code," +
            "  country_translate.name" +
            " FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id" +
            " WHERE drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND" +
            "      country_translate.lan_name = drug_translate.lang_name AND drug_translate.name LIKE ?" +
            " ORDER BY ?";
    private final static String GET_DRUGS = "SELECT" +
            "  drug.id," +
            "  drug_translate.name," +
            "  drug_translate.composition," +
            "  drug.number," +
            "  drug.amount," +
            "  drug.dosage," +
            "  drug_translate.description," +
            "  drug.need_prescription," +
            "  drug.price," +
            "  manufacturer.country_code," +
            "  manufacturer.id," +
            "  manufacturer.phone_number," +
            "  manufacturer.email," +
            "  manufacturer_translate.name," +
            "  manufacturer_translate.address," +
            "  country_translate.name" +
            " FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id" +
            " WHERE drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND" +
            "      country_translate.lan_name = drug_translate.lang_name" +
            " ORDER BY ?" +
            " LIMIT ? OFFSET ?;";
    private static final String ADD_DRUG = "INSERT INTO drug (manufacturer_id, dosage, amount, price, number, need_prescription)" +
            " VALUES (?,?,?,?,?,?);";
    private static final String ADD_DRUG_DESCRIPTION = "INSERT INTO drug_translate (drug_id, lang_name, name, description, composition) VALUES\n" +
            "  (?,?,?,?,?);";

    @Override
    public List<Drug> getDrugList(Language language, int number, int offset, DrugCriteria orderField) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_DRUGS);
            String languageName = language.name().toLowerCase();
            statement.setString(1, languageName);
            statement.setString(2, orderField.toString().toLowerCase());
            statement.setInt(3, number);
            statement.setInt(4, offset);
            ResultSet set = statement.executeQuery();

            DrugBuilder builder = new DrugBuilderImpl(set);

            while (set.next()) {
                builder.create();
                builder.buildDrugInfo();
                builder.buildDrugDescription();
                builder.buildDrugManufacturer();
                drugs.add(builder.get());
            }
            return drugs;
        } catch (SQLException e) {
            logger.error("Not able to get drug list", e);
            throw new DAOException("An error has occurred in attempt of getting drug list from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addDrug(Drug drug, Language language) throws DAOException {
        try {
            addDrugUsingTransaction(drug, language);
        } catch (SQLException e) {
            logger.error("Not able to add drug to db", e);
            throw new DAOException("An error has occurred in attempt of adding drug to database", e);
        }
    }

    private void addDrugUsingTransaction(Drug drug, Language language) throws SQLException, ConnectionPoolException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement addDrugStatement = connection.prepareStatement(ADD_DRUG, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addDrugDescriptionStatement = connection.prepareStatement(ADD_DRUG_DESCRIPTION)) {
            connection.setAutoCommit(false);


            addDrugStatement.setInt(1, drug.getManufacturer().getId());
            addDrugStatement.setInt(2, drug.getDosage());
            addDrugStatement.setInt(3, drug.getAmount());
            addDrugStatement.setDouble(4, drug.getPrice());
            addDrugStatement.setInt(5, drug.getNumber());
            addDrugStatement.setBoolean(6, drug.isNeedPrescription());

            addDrugStatement.executeUpdate();

            ResultSet resultSet = addDrugStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);

                addDrugDescriptionStatement.setInt(1, id);
                addDrugDescriptionStatement.setString(2, language.toString().toLowerCase());
                addDrugDescriptionStatement.setString(3, drug.getName());
                addDrugDescriptionStatement.setString(4, drug.getDescription());
                addDrugDescriptionStatement.setString(5, drug.getComposition());
                addDrugDescriptionStatement.executeUpdate();
            } else {
                SQLException e = new SQLException();
                logger.error("Not able to add drug to db", e);
                throw e;
            }
        } catch (SQLException e) {
            connection.rollback();
            logger.error("Not able to add drug to db", e);
            throw new SQLException("Transaction failed", e);
        } finally {
            connection.setAutoCommit(true);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addDrugDescription(Drug drug, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_DRUG_DESCRIPTION)) {
            statement.setInt(1, drug.getId());
            statement.setString(2, language.toString().toLowerCase());
            statement.setString(3, drug.getName());
            statement.setString(4, drug.getDescription());
            statement.setString(5, drug.getComposition());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add drug description to db", e);
            throw new DAOException("An error has occurred in attempt of adding drug description to database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Drug> findDrugsByManufacturer(int manufacturerId, Language language, int number, int offset, DrugCriteria orderField) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUGS_BY_MANUFACTURER)) {
            List<Drug> drugs = new ArrayList<>();

            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, manufacturerId);
            statement.setInt(3, number);
            statement.setInt(4, offset);
            statement.setString(5, orderField.toString().toLowerCase());

            ResultSet set = statement.executeQuery();
            DrugBuilder builder = new DrugBuilderImpl(set);
            while (set.next()) {
                builder.buildFullDrug();
                drugs.add(builder.get());
            }
            return drugs;
        } catch (SQLException e) {
            logger.error("Not able to find drugs of specified manufacturer", e);
            throw new DAOException("An error has occurred in attempt of find drug list by manufacturer", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeDrugDescription(Drug drug, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_DRUG_DESCRIPTION)) {
            statement.setString(1, drug.getName());
            statement.setString(2, drug.getComposition());
            statement.setString(3, drug.getDescription());
            statement.setInt(4, drug.getId());
            statement.setString(5, language.toString().toLowerCase());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to change drug description", e);
            throw new DAOException("An error has occurred in attempt of change drug description in database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public void changeDrugInfo(Drug drug) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_DRUG_INFO)) {
            statement.setInt(1, drug.getDosage());
            statement.setInt(2, drug.getAmount());
            statement.setInt(3, drug.getNumber());
            statement.setBoolean(4, drug.isNeedPrescription());
            statement.setDouble(5, drug.getPrice());
            statement.setInt(6, drug.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to change drug description", e);
            throw new DAOException("Can't add description", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Drug> findDrugByName(String name, Language language, DrugCriteria orderField) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_DRUG)) {
            List<Drug> drugs = new ArrayList<>();

            statement.setString(1, language.toString().toLowerCase());
            statement.setString(2, "%" + name + "%");
            statement.setString(3, orderField.toString().toLowerCase());

            ResultSet set = statement.executeQuery();
            DrugBuilder builder = new DrugBuilderImpl(set);

            while (set.next()) {
                builder.buildFullDrug();
                drugs.add(builder.get());
            }
            return drugs;
        } catch (SQLException e) {
            logger.error("Not able to find drug with name that includes: " + name, e);
            throw new DAOException("An error has occurred in attempt to find drug by name", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int getDrugCount(Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUG_NUMBER)) {
            statement.setString(1, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt("number");
        } catch (SQLException e) {
            logger.error("Not able to count drug number", e);
            throw new DAOException("An error has occurred in attempt to count drug in db number", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void removeDrug(int drugId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_DRUG)) {
            statement.setInt(1, drugId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to remove drug with id = " + drugId, e);
            throw new DAOException("An error has occurred in attempt of removing drug  from db", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Drug getDrug(int id, Language language) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUG)) {
            statement.setInt(1, id);
            statement.setString(2, language.toString().toLowerCase());

            ResultSet set = statement.executeQuery();
            if (!set.next()) {
                return null;
            }
            DrugBuilder builder = new DrugBuilderImpl(set);
            builder.buildFullDrug();
            return builder.get();
        } catch (SQLException e) {
            logger.error("Not able to get drug with id = " + id + "  from db", e);
            throw new DAOException("An error has occurred in attempt of getting drug from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Drug getDrugInfo(int drugId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUG_INFO)) {
            statement.setInt(1, drugId);
            ResultSet set = statement.executeQuery();
            if (!set.next()) {
                return null;
            }
            DrugBuilder drugBuilder = new DrugBuilderImpl(set);
            drugBuilder.create();
            drugBuilder.buildDrugInfo();
            return drugBuilder.get();
        } catch (SQLException e) {
            logger.error("Not able to get drug with id = " + drugId + " info ", e);
            throw new DAOException("An error has occurred in attempt of getting drug info of drug from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
