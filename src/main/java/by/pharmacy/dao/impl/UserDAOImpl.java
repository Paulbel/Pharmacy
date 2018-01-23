package by.pharmacy.dao.impl;

import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.connectionpool.ConnectionPool;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    private static final String GET_USERS = "SELECT * FROM user LIMIT ? OFFSET ?";

    private static final String GET_USERS_WITH_ROLE = "SELECT * FROM user where user.role = ?";


    private static final String ADD_USER = "INSERT INTO user (login, name, surname, password, email, phone)" +
            " VALUES (?, ?, ?, MD5(?), ?, ?);";

    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";

    private static final String CHANGE_ROLE_BY_LOGIN = "UPDATE user SET role =? WHERE login =?";

    private static final String CHECK_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = md5(?);";

    @Override
    public List<User> getUsers(int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
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
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    public void registration(User user, String password) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
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
            throw new DAOException("User already exists!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public User findUserByLogin(String login) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    @Override
    public void setRole(String login, Role role) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(CHANGE_ROLE_BY_LOGIN);
            statement.setString(1, role.name());
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHECK_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);


            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                throw new DAOException("Wrong data");
            }
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> getUsers(Role role) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet set;
        try (PreparedStatement statement = connection.prepareStatement(GET_USERS_WITH_ROLE)) {
            statement.setString(1, role.toString().toLowerCase());
            set = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }
            return userList;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("user.name");
        String surname = resultSet.getString("user.surname");
        String login = resultSet.getString("user.login");
        Role role = Role.valueOf(resultSet.getString("user.role").toUpperCase());
        String phone = resultSet.getString("user.phone");
        String email = resultSet.getString("user.email");
        User user = new User();

        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }


}
