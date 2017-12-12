package by.pharmacy.dao.impl.SQLDrugDAO;

import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.connectionPool.ConnectionPool;
import by.pharmacy.dao.connectionPool.exception.ConnectionPoolException;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLDrugDAO implements DrugDAO {

    @Override
    public List<Drug> getAll(Language language) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {

            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQLDrugDAOConstant.GET_ALL_DRUG);
            String languageName = language.name().toLowerCase();
            statement.setString(SQLDrugDAOConstant.GET_ALL_DRUG_LANGUAGE_INDEX, languageName);
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
            PreparedStatement statement = connection.prepareStatement(SQLDrugDAOConstant.FIND_LANGUAGE_CODE_BY_NAME);
            String languageName = language.name().toLowerCase();
            statement.setString(SQLDrugDAOConstant.FIND_LANGUAGE_CODE_BY_NAME_NAME_INDEX, languageName);
            ResultSet set = statement.executeQuery();
            set.next();
            String code = set.getString(SQLDrugDAOConstant.LANGUAGE_TABLE_CODE);

            PreparedStatement stat = connection.prepareStatement(SQLDrugDAOConstant.ADD_DRUG, Statement.RETURN_GENERATED_KEYS);

            stat.setBoolean(SQLDrugDAOConstant.ADD_DRUG_NEED_PRESCRIPTION_INDEX, drug.isNeedPresciption());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_MANUFACTURER_ID_INDEX, drug.getManufacturer().getId());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_DOSAGE_INDEX, drug.getDosage());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_AMOUNT_INDEX, drug.getAmount());
            stat.setInt(SQLDrugDAOConstant.ADD_DRUG_NUMBER_INDEX, drug.getNumber());
            stat.setDouble(SQLDrugDAOConstant.ADD_DRUG_PRICE_INDEX, drug.getPrice());
            stat.setBoolean(SQLDrugDAOConstant.ADD_DRUG_NEED_PRESCRIPTION_INDEX, drug.isNeedPresciption());

            stat.executeUpdate();
            connection.setSavepoint();//TODO:transaction
            set = stat.getGeneratedKeys();

            if (set.next()) {
                int id = set.getInt(SQLDrugDAOConstant.DRUG_TABLE_ID);
                statement = connection.prepareStatement(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION);

                statement.setInt(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DRUG_ID_INDEX, id);
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_LANG_CODE_INDEX, code);
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_NAME_INDEX, drug.getName());
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DESCRIPTION_INDEX, drug.getDescription());
                statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_COMPOSITION_INDEX, drug.getComposition());
                statement.executeUpdate();
            }else {
                throw new DAOException("Key wasn't incremented");
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);

        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.closeConnection(connection);
        }

    }

    @Override
    public Drug getDrug(int id, Language language) throws DAOException {
        return null;
    }


    private Drug createDrugFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(SQLDrugDAOConstant.DRUG_TABLE_ID);
        String name = resultSet.getString(SQLDrugDAOConstant.DRUG_TRANSLATE_TABLE_NAME);
        String composition = resultSet.getString(SQLDrugDAOConstant.DRUG_TRANSLATE_TABLE_COMPOSITION);
        int number = resultSet.getInt(SQLDrugDAOConstant.DRUG_TABLE_NUMBER);
        int amount = resultSet.getInt(SQLDrugDAOConstant.DRUG_TABLE_AMOUNT);
        int dosage = resultSet.getInt(SQLDrugDAOConstant.DRUG_TABLE_DOSAGE);
        String description = resultSet.getString(SQLDrugDAOConstant.DRUG_TRANSLATE_TABLE_DESCRIPTION);
        boolean needPrescription = resultSet.getBoolean(SQLDrugDAOConstant.DRUG_TABLE_NEED_PRESCRIPTION);
        double price = resultSet.getDouble(SQLDrugDAOConstant.DRUG_TABLE_PRICE);
        //TODO:manufacturer logic
        Drug drug = new Drug();
        drug.setId(id);
        drug.setName(name);
        drug.setComposition(composition);
        drug.setNumber(number);
        drug.setAmount(amount);
        drug.setDosage(dosage);
        drug.setDescription(description);
        drug.setNeedPresciption(needPrescription);
        drug.setPrice(price);
        return drug;
    }
}
