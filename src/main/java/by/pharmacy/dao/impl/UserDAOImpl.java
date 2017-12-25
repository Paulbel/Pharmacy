package by.pharmacy.dao.impl;

import by.pharmacy.dao.DAOConstant;
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

    @Override
    public List<User> getUsers(int number, int offset) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet set;
        try (PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.GET_USERS)) {
            statement.setInt(SQLUserDAOConstant.GET_USERS_START_INDEX, number);
            statement.setInt(SQLUserDAOConstant.GET_USERS_OFFSET_INDEX, offset);
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


    public void registration(User user) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.ADD_USER)){
            statement.setString(SQLUserDAOConstant.ADD_USER_NAME_INDEX, user.getName());
            statement.setString(SQLUserDAOConstant.ADD_USER_SURNAME_INDEX, user.getSurname());
            statement.setString(SQLUserDAOConstant.ADD_USER_PATRONYMIC_INDEX, user.getPatronymic());
            statement.setString(SQLUserDAOConstant.ADD_USER_LOGIN_INDEX, user.getLogin());
            statement.setString(SQLUserDAOConstant.ADD_USER_PASSWORD_INDEX, user.getPassword());
            statement.setString(SQLUserDAOConstant.ADD_USER_PHONE_INDEX, user.getPhoneNumber());
            statement.setString(SQLUserDAOConstant.ADD_USER_EMAIL_INDEX, user.getEmail());
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
        try (PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.FIND_USER_BY_LOGIN)) {
            statement.setString(SQLUserDAOConstant.FIND_USER_BY_LOGIN_INDEX, login);
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
            PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.CHANGE_ROLE_BY_LOGIN);

            statement.setString(SQLUserDAOConstant.CHANGE_ROLE_BY_LOGIN_ROLE_INDEX, role.name());
            statement.setString(SQLUserDAOConstant.CHANGE_ROLE_BY_LOGIN_LOGIN_INDEX, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(DAOConstant.USER_TABLE_NAME);
        String surname = resultSet.getString(DAOConstant.USER_TABLE_SURNAME);
        String patronymic = resultSet.getString(DAOConstant.USER_TABLE_PATRONYMIC);
        String login = resultSet.getString(DAOConstant.USER_TABLE_LOGIN);
        String password = resultSet.getString(DAOConstant.USER_TABLE_PASSWORD);
        Role role = Role.valueOf(resultSet.getString(DAOConstant.USER_TABLE_ROLE).toUpperCase());
        String phone = resultSet.getString(DAOConstant.USER_TABLE_PHONE);
        String email = resultSet.getString(DAOConstant.USER_TABLE_EMAIL);
        User user = new User();

        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }


}
