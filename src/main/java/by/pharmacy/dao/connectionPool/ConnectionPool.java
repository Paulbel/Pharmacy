package by.pharmacy.dao.connectionPool;


import by.pharmacy.dao.connectionPool.exception.ConnectionPoolException;
import by.pharmacy.dao.impl.SQLConstantCommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenConnectionQueue;

    private int poolSize;
    private String driverName;
    private String url;
    private String user;
    private String password;

    private static volatile ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance()  {
        return instance;
    }

    private ConnectionPool() {
        ResourceBundle resourceBundle =  ResourceBundle.getBundle(SQLConstantCommon.CURRENT_DB);
        this.driverName = resourceBundle.getString(SQLConstantCommon.CURRENT_DB+"."+SQLConstantCommon.DRIVER_NAME);
        this.url = resourceBundle.getString(SQLConstantCommon.CURRENT_DB+"."+SQLConstantCommon.URL);
        this.user = resourceBundle.getString(SQLConstantCommon.CURRENT_DB+"."+SQLConstantCommon.USER);
        this.password = resourceBundle.getString(SQLConstantCommon.CURRENT_DB+"."+SQLConstantCommon.PASSWORD);
        this.poolSize = Integer.valueOf(resourceBundle.getString(SQLConstantCommon.CURRENT_DB+"."+SQLConstantCommon.POOL_SIZE));
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