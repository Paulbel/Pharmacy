package by.pharmacy.dao.connectionpool;


import by.pharmacy.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private final static String CURRENT_DB = "mysql";
    private final static String DRIVER_NAME = "driver";
    private final static String URL = "url";
    private final static String USER = "user";
    private final static String PASSWORD = "password";
    private final static String POOL_SIZE = "pool_size";

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenConnectionQueue;

    private static volatile ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(CURRENT_DB);
        final String driverName = resourceBundle.getString(CURRENT_DB + "." + DRIVER_NAME);
        final String url = resourceBundle.getString(CURRENT_DB + "." + URL);
        final String user = resourceBundle.getString(CURRENT_DB + "." + USER);
        final String password = resourceBundle.getString(CURRENT_DB + "." + PASSWORD);
        int poolSize = Integer.valueOf(resourceBundle.getString(CURRENT_DB + "." + POOL_SIZE));
        try {
            Class.forName(driverName);
            givenConnectionQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Can't get access to database", e);
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void closeConnection(Connection connection) throws ConnectionPoolException {
        try {
            if (connection.isClosed()) {
                throw new ConnectionPoolException("Trying to close closed connection");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Can't access connection", e);
        }

        if (!givenConnectionQueue.remove(connection)) {
            throw new ConnectionPoolException("Error deleting connection from the given away connections pool");
        }

        if (!connectionQueue.offer(connection)) {
            throw new ConnectionPoolException("Error allocating connection in the pool");
        }
    }

    public void dispose() throws ConnectionPoolException {
        try {
            closeConnectionsQueue(givenConnectionQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Closing connection error", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = connectionQueue.take();
            givenConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source", e);
        }
        return connection;
    }


    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }
}