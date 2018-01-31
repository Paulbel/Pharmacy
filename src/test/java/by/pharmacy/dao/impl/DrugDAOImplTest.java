package by.pharmacy.dao.impl;

import by.pharmacy.BaseDAOTest;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.dao.impl.drug.DrugDAOImpl;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DrugDAOImplTest extends BaseDAOTest {
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
            "  manufacturer_translate.name," +
            "  manufacturer_translate.address," +
            "  manufacturer.email," +
            "  country_translate.name" +
            " FROM drug" +
            "  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id" +
            "  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id" +
            "  INNER JOIN country ON manufacturer.country_code = country.code" +
            "  INNER JOIN country_translate ON country.code = country_translate.country_code" +
            "  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id" +
            " WHERE drug.id = ? AND drug_translate.lang_name = ? AND manufacturer_translate.language_name = drug_translate.lang_name AND" +
            "      country_translate.lan_name = drug_translate.lang_name;";

    private final static String GET_NUMBER = "SELECT AVG(drug.id) AS number FROM drug";


    @Test
    public void testGetDrugs() throws Exception {
        DrugDAO drugDAO = new DrugDAOImpl();
        List<Drug> expectedList = drugs.subList(0, 2);
        assertEquals(expectedList, drugDAO.getDrugs(Language.RU, 10, 0));
    }

    @Test
    public void testAddDescription() throws Exception{
/*        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_DRUG)) {

            DrugDAO drugDAO = new DrugDAOImpl();


            Drug drug = new Drug();
            drug.setName("name");
            drug.setId(1);
            drug.setDescription("description");
            drug.setComposition("composition");


            Drug expectedDrug = drugs.get(2);
            drugDAO.addDrug(expectedDrug, Language.RUSSIAN);
            statement.setInt(1, 3);
            statement.setString(2, "russian");
            ResultSet resultSet = statement.executeQuery();
            assertEquals(resultSet.next(), true);
            Drug drug = createDrug(resultSet);
            assertEquals(expectedDrug, drug);
        }*/
    }

    @Test
    public void testAddDrug() throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_DRUG)) {
            DrugDAO drugDAO = new DrugDAOImpl();
            Drug expectedDrug = drugs.get(2);
            drugDAO.addDrug(expectedDrug, Language.RU);
            statement.setInt(1, 3);
            statement.setString(2, "russian");
            ResultSet resultSet = statement.executeQuery();
            assertEquals(resultSet.next(), true);
            Drug drug = createDrug(resultSet);
            assertEquals(expectedDrug, drug);
        }
    }

    @Test
    @Ignore
    public void testFindDrugs() throws Exception {
        DrugDAO drugDAO = new DrugDAOImpl();
        List<Drug> expectedList = drugs.subList(0, 1);
        //assertEquals(expectedList, drugDAO.findDrugsByName("моно", Language.RUSSIAN, 10, 0));
    }



    @Test
    public void testRemoveDrug() throws Exception {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_NUMBER);
            resultSet.next();
            int number = resultSet.getInt("number");
            assertEquals(1, number);
        }

    }

    private Drug createDrug(ResultSet resultSet) throws SQLException {
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


        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setAddress(address);
        manufacturer.setName(manufacturerName);
        manufacturer.setPhoneNumber(phoneNumber);
        //manufacturer.setCountry(countryName);
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