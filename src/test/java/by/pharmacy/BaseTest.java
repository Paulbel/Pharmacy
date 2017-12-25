package by.pharmacy;

import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class BaseTest {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("test");



    @Before
    public void initDB() throws ClassNotFoundException {
        String driverName = resourceBundle.getString("test.driver");
        Class.forName(driverName);
    }

    protected Connection getConnection() throws SQLException {
        String url = resourceBundle.getString("test.url");
        String user = resourceBundle.getString("test.user");
        String password = resourceBundle.getString("test.password");

        return DriverManager.getConnection(url, user, password);
    }

}
