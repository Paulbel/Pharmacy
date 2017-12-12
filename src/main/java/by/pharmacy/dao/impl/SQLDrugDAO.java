package by.pharmacy.dao.impl;

import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.SQLConnectionCreator;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLDrugDAO implements DrugDAO {

    @Override
    public List<Drug> getAll(Language language) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            List<Drug> drugs = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQLDrugDAOConstant.GET_ALL_DRUG);
            String languageName = language.name().toLowerCase();
            statement.setString(SQLDrugDAOConstant.GET_ALL_DRUG_LANGUAGE_INDEX,languageName);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                drugs.add(createDrugFromResultSet(set));
            }
            return drugs;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addDrug(Drug drug, Language language) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
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
            connection.setSavepoint();
            ResultSet set1 = stat.getGeneratedKeys();

            set1.next();
            int id = set1.getInt(1);


            statement = connection.prepareStatement(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION);

            statement.setInt(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DRUG_ID_INDEX, id);
            statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_LANG_CODE_INDEX, code);
            statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_NAME_INDEX, drug.getName());
            statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_DESCRIPTION_INDEX, drug.getDescription());
            statement.setString(SQLDrugDAOConstant.ADD_DRUG_DESCRIPTION_COMPOSITION_INDEX, drug.getComposition());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);

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
        Drug drug =new Drug();
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
