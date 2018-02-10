package by.pharmacy;

import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseDAOTest {
    protected List<User> users = new ArrayList<>();
    protected List<Drug> drugs = new ArrayList<>();
    private static final String DELETE_ALL_FROM_DRUG = "DELETE FROM drug";
    private static final String DELETE_ALL_FROM_MANUFACTURER = "DELETE FROM manufacturer";
    private static final String DELETE_ALL_FROM_COUNTRY = "DELETE FROM country";
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("test");
    private static final String SET_AUTO_INCREMENT_MANUFACTURER_ID_ZERO = "ALTER TABLE manufacturer AUTO_INCREMENT=1";
    private static final String SET_AUTO_INCREMENT_DRUG_ID_ZERO = "ALTER TABLE drug AUTO_INCREMENT=1";


    private static final String ADD_COUNTRY = "INSERT INTO country (code) VALUES " +
            "('de')," +
            "('by');";

    private static final String ADD_COUNTRY_NAME = "INSERT INTO country_translate (country_code, lan_name, name) VALUES " +
            "('de', 'russian', 'Федеративная Республика Германия')," +
            "('by', 'russian', 'Республика Беларусь');";
    private static final String ADD_MANUFACTURERS = "INSERT INTO manufacturer (phone_number, country_code, email) VALUES " +
            "('', 'de', '')," +
            "('', 'de', '');";

    private static final String ADD_MANUFACTURERS_DESCRIPTIONS = "INSERT INTO manufacturer_translate (language_name, manufacturer_id, name, address) VALUES " +
            "('russian', 1, 'Вёрваг Фарма', '')," +
            "('russian', 2, 'Натурварен', '');";

    private static final String ADD_DRUGS = "INSERT INTO drug (manufacturer_id, dosage, amount,  number,price, need_prescription) VALUES" +
            " (1, 31, 77, 682, 28.56, 1)," +
            " (2, 6, 19, 277, 4.81, 0);";

    private static final String ADD_DRUGS_DESCRIPTION = "INSERT INTO drug_translate (drug_id, lang_name, name, description, composition) VALUES " +
            "(1, 'russian', 'Мильгамма моно 300', 'Описание препарата Мильгамма моно 300', 'Состав препарата Мильгамма моно 300')," +
            "(2, 'russian', 'Доктор тайсс геровитал', 'Описание препарата Доктор тайсс геровитал', 'Состав препарата Доктор тайсс геровитал');";

    private static final String ADD_LANGUAGE = "INSERT INTO language (language_name) VALUES" +
            "  ('russian');";
    private static final String DELETE_LANGUAGES = "DELETE FROM language";

    private static final String ADD_USERS = "INSERT INTO user (login,password,name,surname,phone,email) VALUES " +
            "('login8o5vj4mdkd','54157515','Генрих','Синельников','+375656381587','8o5vj4mdkd@gmail.com'), " +
            "('loginxl5k8m24lx','32880767','Алексей','Гапеенко','+375410634274','xl5k8m24lx@gmail.com');";
    private static final String REMOVE_USERS = "DELETE FROM test.user";


    @Before
    public void init() throws ClassNotFoundException, SQLException {
//        String driverName = resourceBundle.getString("test.driver");
//        Class.forName(driverName);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(ADD_LANGUAGE);
            this.initUsers();
            statement.executeUpdate(ADD_USERS);
            statement.executeUpdate(SET_AUTO_INCREMENT_MANUFACTURER_ID_ZERO);
            statement.executeUpdate(SET_AUTO_INCREMENT_DRUG_ID_ZERO);
            statement.executeUpdate(ADD_COUNTRY);
            statement.executeUpdate(ADD_COUNTRY_NAME);

            statement.executeUpdate(ADD_MANUFACTURERS);
            statement.executeUpdate(ADD_MANUFACTURERS_DESCRIPTIONS);

            statement.executeUpdate(ADD_DRUGS);
            statement.executeUpdate(ADD_DRUGS_DESCRIPTION);
            this.initDrugs();
        }
    }

    @After
    public void clean() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_LANGUAGES);
            this.initUsers();

            statement.executeUpdate(REMOVE_USERS);
            statement.executeUpdate(DELETE_ALL_FROM_DRUG);
            statement.executeUpdate(DELETE_ALL_FROM_MANUFACTURER);
            statement.executeUpdate(DELETE_ALL_FROM_COUNTRY);

        }
    }

    protected Connection getConnection() throws SQLException {
        String url = resourceBundle.getString("test.url");
        String user = resourceBundle.getString("test.user");
        String password = resourceBundle.getString("test.password");

        return DriverManager.getConnection(url, user, password);
    }

    private void initUsers() {
        this.users = new ArrayList<>();

        User user1 = new User();
        user1.setLogin("login8o5vj4mdkd");
       // user1.setPassword("54157515");
        user1.setName("Генрих");
        user1.setSurname("Синельников");
        user1.setPhoneNumber("+375656381587");
        user1.setEmail("8o5vj4mdkd@gmail.com");
        user1.setRole(UserRole.CLIENT);

        User user2 = new User();
        user2.setLogin("loginxl5k8m24lx");
        //user2.setPassword("32880767");
        user2.setName("Алексей");
        user2.setSurname("Гапеенко");
        user2.setPhoneNumber("+375410634274");
        user2.setEmail("xl5k8m24lx@gmail.com");
        user2.setRole(UserRole.CLIENT);

        User user3 = new User();
        user3.setLogin("login4o47zru1qt");
       // user3.setPassword("13718854");
        user3.setName("Евгений");
        user3.setSurname("Артемьев");
        user3.setPhoneNumber("+375070676621");
        user3.setEmail("4o47zru1qt@gmail.com");
        user3.setRole(UserRole.CLIENT);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    private void initDrugs() {
        Drug drug = new Drug();
        drug.setName("Мильгамма моно 300");
        drug.setDescription("Описание препарата Мильгамма моно 300");
        drug.setComposition("Состав препарата Мильгамма моно 300");
        drug.setPrice(28.56);
        drug.setId(1);
        drug.setDosage(31);
        drug.setAmount(77);
        drug.setNumber(682);
        drug.setNeedPrescription(true);

        Manufacturer manufacturer1 = new Manufacturer();
       // manufacturer1.setCountry("Федеративная Республика Германия");
        manufacturer1.setName("Вёрваг Фарма");
        manufacturer1.setEmail("");
        manufacturer1.setPhoneNumber("");
        manufacturer1.setAddress("");
        manufacturer1.setId(1);
        drug.setManufacturer(manufacturer1);
        drugs.add(drug);

        drug = new Drug();
        drug.setName("Доктор тайсс геровитал");
        drug.setDescription("Описание препарата Доктор тайсс геровитал");
        drug.setComposition("Состав препарата Доктор тайсс геровитал");
        drug.setPrice(4.81);
        drug.setId(2);
        drug.setDosage(6);
        drug.setAmount(19);
        drug.setNumber(277);
        drug.setNeedPrescription(false);

        Manufacturer manufacturer2 = new Manufacturer();
        //manufacturer2.setCountry("Федеративная Республика Германия");
        manufacturer2.setName("Натурварен");
        manufacturer2.setEmail("");
        manufacturer2.setPhoneNumber("");
        manufacturer2.setAddress("");
        manufacturer2.setId(2);
        drug.setManufacturer(manufacturer2);
        drugs.add(drug);


        drug = new Drug();
        drug.setName("Пантовигар");
        drug.setDescription("Описание препарата Пантовигар");
        drug.setComposition("Состав препарата Пантовигар");
        drug.setPrice(36.69);
        drug.setId(3);
        drug.setDosage(48);
        drug.setAmount(60);
        drug.setNumber(832);
        drug.setNeedPrescription(true);


        drug.setManufacturer(manufacturer1);
        drugs.add(drug);
    }
}
