package by.pharmacy.dao.impl.drug;

import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.connectionpool.exception.ConnectionPoolException;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.dao.impl.drug.builder.DrugBuildDirector;
import by.pharmacy.dao.impl.drug.builder.drugBuilder.DrugBuilder;
import by.pharmacy.dao.impl.drug.builder.drugBuilder.impl.DrugFullBuilder;
import by.pharmacy.entity.Country;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DrugDAOImpl implements DrugDAO {
    private final static String REMOVE_DRUG = "DELETE FROM drug WHERE id = ?";


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


    private final static String GET_DRUG_NUMBER = "SELECT count(drug.id) AS number FROM drug" +
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
            "      country_translate.lan_name = drug_translate.lang_name AND drug_translate.name LIKE ?";


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
            " ORDER BY drug_translate.name" +
            " LIMIT ? OFFSET ?;";

    private static final String ADD_DRUG = "INSERT INTO drug (manufacturer_id, dosage, amount, price, number, need_prescription)" +
            " VALUES (?,?,?,?,?,?);";

    private static final String ADD_DRUG_DESCRIPTION = "INSERT INTO drug_translate (drug_id, lang_name, name, description, composition) VALUES\n" +
            "  (?,?,?,?,?);";

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_DRUGS);
            String languageName = language.name().toLowerCase();
            statement.setString(1, languageName);
            statement.setInt(2, number);
            statement.setInt(3, offset);
            ResultSet set = statement.executeQuery();
            DrugBuilder drugBuilder = new DrugFullBuilder(set);
            DrugBuildDirector drugBuildDirector = new DrugBuildDirector(drugBuilder);

            while (set.next()) {
                drugBuildDirector.build();
                drugs.add(drugBuilder.getDrug());
            }
            return drugs;
        } catch (SQLException e) {
            throw new DAOException("Can't get drugs", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void addDrug(Drug drug, Language language) throws DAOException {
        try {
            addDrugUsingTransaction(drug, language);
        } catch (SQLException e) {
            throw new DAOException("Can't add drug to database");
        }
    }

    @Override
    public void addDrugDescription(Drug drug, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_DRUG_DESCRIPTION)) {
            statement.setInt(1, drug.getId());
            statement.setString(2, language.toString().toLowerCase());
            statement.setString(3, drug.getName());
            statement.setString(4, drug.getDescription());
            statement.setString(5, drug.getComposition());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can't add description", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private void addDrugUsingTransaction(Drug drug, Language language) throws SQLException, ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_DRUG, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, drug.getManufacturer().getId());
            statement.setInt(2, drug.getDosage());
            statement.setInt(3, drug.getAmount());
            statement.setDouble(4, drug.getPrice());
            statement.setInt(5, drug.getNumber());
            statement.setBoolean(6, drug.isNeedPrescription());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {

                int id = resultSet.getInt(1);

                statement = connection.prepareStatement(ADD_DRUG_DESCRIPTION);

                statement.setInt(1, id);
                statement.setString(2, language.toString().toLowerCase());
                statement.setString(3, drug.getName());
                statement.setString(4, drug.getDescription());
                statement.setString(5, drug.getComposition());
                statement.executeUpdate();
            } else {
                throw new SQLException("Auto increment failed");
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Transaction failed", e);
        } finally {
            connection.setAutoCommit(true);

            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Drug> findDrugsByManufacturer(int manufacturerId, Language language, int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUGS_BY_MANUFACTURER)) {
            List<Drug> drugs = new ArrayList<>();
            statement.setString(1, language.toString().toLowerCase());
            statement.setInt(2, manufacturerId);
            statement.setInt(3, number);
            statement.setInt(4, offset);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                drugs.add(createDrugFromResultSet(set));
            }
            return drugs;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void changeDrugDescription(Drug drug, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_DRUG_DESCRIPTION)) {
            statement.setString(1, drug.getName());
            statement.setString(2, drug.getComposition());
            statement.setString(3, drug.getDescription());
            statement.setInt(4, drug.getId());
            statement.setString(5, language.toString().toLowerCase());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can't add description", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

    }


    @Override
    public void changeDrugInfo(Drug drug) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
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
            throw new DAOException("Can't add description", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Drug> findDrugByName(String name, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_DRUG)) {
            List<Drug> drugs = new ArrayList<>();

            statement.setString(1, language.toString().toLowerCase());
            statement.setString(2, "%" + name + "%");


            ResultSet set = statement.executeQuery();
            while (set.next()) {
                drugs.add(createDrugFromResultSet(set));
            }
            return drugs;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public int getDrugCount(Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUG_NUMBER)) {
            statement.setString(1, language.toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DAOException("Can't get number of entries");
            }
            return resultSet.getInt("number");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void removeDrug(int drugId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_DRUG)) {
            statement.setInt(1, drugId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can't remove drug from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public Drug getDrug(int id, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_DRUG)) {
            statement.setInt(1, id);
            statement.setString(2, language.toString().toLowerCase());

            ResultSet set = statement.executeQuery();
            set.next();
            return createDrugFromResultSet(set);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    private Drug createDrugFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("drug.id");
        String name = resultSet.getString("drug_translate.name");
        String composition = resultSet.getString("drug_translate.composition");
        int number = resultSet.getInt("drug.number");
        int amount = resultSet.getInt("drug.amount");
        int dosage = resultSet.getInt("drug.dosage");
        String description = resultSet.getString("drug_translate.description");
        boolean needPrescription = resultSet.getBoolean("drug.need_prescription");
        double price = resultSet.getDouble("drug.price");

        int manufacturerId = resultSet.getInt("manufacturer.id");
        String phoneNumber = resultSet.getString("manufacturer.phone_number");
        String manufacturerName = resultSet.getString("manufacturer_translate.name");
        String address = resultSet.getString("manufacturer_translate.address");
        String email = resultSet.getString("manufacturer.email");
        String countryName = resultSet.getString("country_translate.name");
        String countryCode = resultSet.getString("manufacturer.country_code");
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setAddress(address);
        manufacturer.setName(manufacturerName);
        manufacturer.setPhoneNumber(phoneNumber);
        Country country = new Country();
        country.setName(countryName);
        country.setCode(countryCode);
        manufacturer.setCountry(country);
        manufacturer.setEmail(email);

        Drug drug = new Drug();
        drug.setId(id);
        drug.setName(name);
        drug.setComposition(composition);
        drug.setNumber(number);
        drug.setAmount(amount);
        drug.setDosage(dosage);
        drug.setDescription(description);
        drug.setNeedPrescription(needPrescription);
        drug.setPrice(price);
        drug.setManufacturer(manufacturer);
        return drug;
    }
}
