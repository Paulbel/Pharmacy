package by.optics.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnectionCreator {
    private static final String HOST = "localhost";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String PORT = "3306";
    private static final String DB_NAME = "optics";

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ HOST +":"+ PORT +"/"+
                        DB_NAME+"?autoReconnect=true&useSSL=false", LOGIN, PASSWORD);

        return connection;
    }

    private SQLConnectionCreator() {
    }
}
