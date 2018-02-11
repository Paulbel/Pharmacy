package by.pharmacy;

import by.pharmacy.entity.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BaseDAOTest {
    protected static final List<User> userList = new ArrayList<>();
    protected static final List<Drug> drugList = new ArrayList<>();
    protected static final List<Manufacturer> manufacturerList = new ArrayList<>();
    private static final String DROP_DATABASE = "DROP DATABASE test;";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("test");

    @BeforeClass
    public static void initDatabaseTables() throws IOException, SQLException, ClassNotFoundException {
        User user;
        Drug drug;
        Manufacturer manufacturer;
        for (int index = 1; index <= 10; index++) {
            user = new User();
            user.setLogin("login" + index);
            user.setRole(UserRole.CLIENT);
            user.setPhoneNumber("phone" + index);
            user.setEmail("email" + index);
            user.setSurname("surname" + index);
            user.setName("name" + index);
            userList.add(user);

            drug = new Drug();
            drug.setName("name" + index);
            drug.setNumber(index + 10);
            drug.setComposition("composition" + index);
            drug.setPrice(index);
            drug.setNeedPrescription(false);
            drug.setDosage(index + 20);
            drug.setDescription("description" + index);

            manufacturer = new Manufacturer();
/*            manufacturer.setId();*/
            manufacturer.setAddress("address" + index);
            manufacturer.setEmail("email" + index);
            manufacturer.setName("name" + 1);
            manufacturer.setPhoneNumber("phone" + 1);
            Country country = new Country();
/*            manufacturer.setCountry();*/


            drugList.add(drug);
        }


        final String driverName = resourceBundle.getString("test.driver");

        Class.forName(driverName);
        String url = resourceBundle.getString("test.url");
        String userName = resourceBundle.getString("test.user");
        String password = resourceBundle.getString("test.password");

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {

            URL fileURL = BaseDAOTest.class.getClassLoader().getResource("base.sql");


            assert fileURL != null;
            Scanner scan = new Scanner(new File(fileURL.getPath()));
            scan.useDelimiter(";");
            while (scan.hasNext()) {
                String str = scan.next();
                if (!str.trim().isEmpty()) {
                    statement.executeUpdate(str + ";");
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException("Can't get access to database", e);
        }


    }


    @AfterClass
    public static void clean() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_DATABASE);
        }
    }

    protected static Connection getConnection() throws SQLException {
        String url = resourceBundle.getString("test.test_url");
        String user = resourceBundle.getString("test.user");
        String password = resourceBundle.getString("test.password");

        return DriverManager.getConnection(url, user, password);
    }
}
