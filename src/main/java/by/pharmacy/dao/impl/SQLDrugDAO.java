package by.pharmacy.dao.impl;

import by.pharmacy.dao.DAOConstant;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLDrugDAO implements DrugDAO {
    private final static String GET_DRUGS = "SELECT\n" +
            "  drug.id,\n" +
            "  drug_translate.name,\n" +
            "  drug_translate.composition,\n" +
            "  drug.number,\n" +
            "  drug.amount,\n" +
            "  drug.dosage,\n" +
            "  drug_translate.description,\n" +
            "  drug.need_prescription,\n" +
            "  drug.price,\n" +
            "  manufacturer.id,\n" +
            "  manufacturer.phone_number,\n" +
            "  manufacturer_translate.name,\n" +
            "  country_translate.name\n" +
            "FROM drug\n" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id\n" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id\n" +
            "  INNER JOIN country ON manufacturer.country_code = country.code\n" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code\n" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id\n" +
            "WHERE drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND\n" +
            "      country_translate.lan_name = drug_translate.lang_name\n" +
            "LIMIT ? OFFSET ?;";
    private final static int GET_DRUGS_LANGUAGE_INDEX = 1;
    private final static int GET_DRUGS_NUMBER_INDEX = 2;
    private final static int GET_DRUGS_OFFSET_INDEX = 3;

    @Override
    public List<Drug> getDrugs(Language language, int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_DRUGS);
            String languageName = language.name().toLowerCase();
            statement.setString(GET_DRUGS_LANGUAGE_INDEX, languageName);
            statement.setInt(GET_DRUGS_NUMBER_INDEX, number);
            statement.setInt(GET_DRUGS_OFFSET_INDEX, offset);
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
    public void addDrug(Drug drug, Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(DAOConstant.FIND_LANGUAGE_CODE_BY_NAME);
            String languageName = language.name().toLowerCase();
            statement.setString(DAOConstant.FIND_LANGUAGE_CODE_BY_NAME_NAME_INDEX, languageName);
            ResultSet set = statement.executeQuery();
            set.next();
            String code = set.getString(DAOConstant.LANGUAGE_TABLE_CODE);

            PreparedStatement stat = connection.prepareStatement(SQLDrugDAOConstant.ADD_DRUG, Statement.RETURN_GENERATED_KEYS);

            stat.setBoolean(SQLDrugDAOConstant.ADD_DRUG_NEED_PRESCRIPTION_INDEX, drug.isNeedPrescription());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_MANUFACTURER_ID_INDEX, drug.getManufacturer().getId());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_DOSAGE_INDEX, drug.getDosage());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_AMOUNT_INDEX, drug.getAmount());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_NUMBER_INDEX, drug.getNumber());
            stat.setDouble(SQLDrugDAOConstant.ADD_DRUG_PRICE_INDEX, drug.getPrice());
            stat.setBoolean(SQLDrugDAOConstant.ADD_DRUG_NEED_PRESCRIPTION_INDEX, drug.isNeedPrescription());

            stat.executeUpdate();

            set = stat.getGeneratedKeys();
            if (set.next()) {
                int id = set.getInt(1);//I dont know why not
                statement = connection.prepareStatement(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION);
                statement.setInt(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DRUG_ID_INDEX, id);
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_LANG_CODE_INDEX, code);
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_NAME_INDEX, drug.getName());
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DESCRIPTION_INDEX, drug.getDescription());
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_COMPOSITION_INDEX, drug.getComposition());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.closeConnection(connection);
        }
    }


    @Override
    public List<Drug> findDrugs(String name) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQLDrugDAOConstant.FIND_DRUG);
            statement.setString(SQLDrugDAOConstant.FIND_DRUG_NAME_INDEX, "%" + name + "%");
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


    private Drug createDrugFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(DAOConstant.DRUG_TABLE_ID);
        String name = resultSet.getString(DAOConstant.DRUG_TRANSLATE_TABLE_NAME);
        String composition = resultSet.getString(DAOConstant.DRUG_TRANSLATE_TABLE_COMPOSITION);
        int number = resultSet.getInt(DAOConstant.DRUG_TABLE_NUMBER);
        int amount = resultSet.getInt(DAOConstant.DRUG_TABLE_AMOUNT);
        int dosage = resultSet.getInt(DAOConstant.DRUG_TABLE_DOSAGE);
        String description = resultSet.getString(DAOConstant.DRUG_TRANSLATE_TABLE_DESCRIPTION);
        boolean needPrescription = resultSet.getBoolean(DAOConstant.DRUG_TABLE_NEED_PRESCRIPTION);
        double price = resultSet.getDouble(DAOConstant.DRUG_TABLE_PRICE);
        //TODO:manufacturer logic
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
        return drug;
    }
}
