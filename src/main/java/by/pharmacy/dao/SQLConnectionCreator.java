package by.pharmacy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class SQLConnectionCreator {
    private static SQLConnectionCreator connectionCreator = new SQLConnectionCreator();
    private static final String dbms = "mysql";
    private static final String HOST = "localhost";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String PORT = "3306";
    private static final String DB_NAME = "pharmacy";
    private static final String DRIVER_PATH = "com.mysql.jdbc.Driver";
    private static boolean driverLoaded = false;


/*    public static Connection createConnection() throws SQLException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" +
                DB_NAME + "?autoReconnect=true&useSSL=false", LOGIN, PASSWORD);

        return connection;
    }*/

    public static Connection createConnection() throws SQLException {
        if (!driverLoaded) {
            try {
                Class.forName(DRIVER_PATH);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            driverLoaded = true;
        }
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", LOGIN);
        connectionProps.put("password",PASSWORD);

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?autoReconnect=true&useSSL=false","root", "root");
        return con;
    }


    private SQLConnectionCreator() {
    }
}
