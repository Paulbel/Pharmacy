package by.optics.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnectionCreator {
    private static SQLConnectionCreator connectionCreator = new SQLConnectionCreator();
    private static final String HOST = "localhost";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String PORT = "3306";
    private static final String DB_NAME = "optics";
    private static final String DRIVER_PATH = "com.mysql.jdbc.Driver";
    private static boolean driverLoaded = false;


    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        if (!driverLoaded) {
            Class.forName(DRIVER_PATH);
            driverLoaded = true;
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" +
                DB_NAME + "?autoReconnect=true&useSSL=false", LOGIN, PASSWORD);

        return connection;
    }

    private SQLConnectionCreator() {
    }
}
