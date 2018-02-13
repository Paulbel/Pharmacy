package by.pharmacy.dao.impl;

import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private final static String GET_USERS = "SELECT * FROM user LIMIT ? OFFSET ?";
    private final static String GET_USERS_WITH_ROLE = "SELECT * FROM user WHERE user.role = ?";
    private final static String ADD_USER = "INSERT INTO user (login, name, surname, password, email, phone)" +
            " VALUES (?, ?, ?, MD5(?), ?, ?);";
    private final static String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";
    private final static String CHANGE_ROLE_BY_LOGIN = "UPDATE user SET role =? WHERE login =?";
    private final static String CHECK_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = md5(?);";
    private final static String FIND_USER = "SELECT * FROM user WHERE name LIKE ? OR surname LIKE ?";


    @Override
    public List<User> getUserList(int number, int offset) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ResultSet set;
        try (PreparedStatement statement = connection.prepareStatement(GET_USERS)) {
            statement.setInt(1, number);
            statement.setInt(2, offset);
            set = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }
            return userList;
        } catch (SQLException e) {
            logger.error("Not able to get user list", e);
            throw new DAOException("An error has occurred in attempt of getting user list from database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void signUp(User user, String password) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, password);
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add new user", e);
            throw new DAOException("An error has occurred in attempt of adding user to database, check if user not exists", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public User findUserByLogin(String login) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Not able to find user", e);
            throw new DAOException("An error has occurred in attempt of finding user in database", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void setRole(String login, UserRole role) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(CHANGE_ROLE_BY_LOGIN);
            statement.setString(1, role.name());
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to set role to user with id = " + login, e);
            throw new DAOException("An error has occurred in attempt of setting user role check if user exists", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHECK_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("Not able to sign in for user with login = " + login, e);
            throw new DAOException("An error has occurred in attempt of signing in", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> getUserList(UserRole role) throws DAOException {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(GET_USERS_WITH_ROLE)) {
            statement.setString(1, role.toString().toLowerCase());
            ResultSet set = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }
            return userList;
        } catch (SQLException e) {
            logger.error("Not able to get user list", e);
            throw new DAOException("An error has occurred in attempt getting user list", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> findUser(String namePart) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER)) {
            statement.setString(1, "%" + namePart + "%");
            statement.setString(2, "%" + namePart + "%");
            ResultSet set = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }
            return userList;
        } catch (SQLException e) {
            logger.error("Not able to get user list", e);
            throw new DAOException("An error has occurred in attempt getting user list", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("user.name"));
        user.setSurname(resultSet.getString("user.surname"));
        user.setLogin(resultSet.getString("user.login"));
        user.setRole(UserRole.valueOf(resultSet.getString("user.role").toUpperCase()));
        user.setPhoneNumber(resultSet.getString("user.phone"));
        user.setEmail(resultSet.getString("user.email"));
        return user;
    }
}
