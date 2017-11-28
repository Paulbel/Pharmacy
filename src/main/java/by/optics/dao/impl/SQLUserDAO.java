package by.optics.dao.impl;

import by.optics.dao.DAOConstant;
import by.optics.dao.SQLConnectionCreator;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.Role;
import by.optics.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.optics.dao.DAOConstant.*;

public class SQLUserDAO implements UserDAO {

    public int findNumberOfUsersWithLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(DAOConstant.COUNT_USERS_WITH_LOGIN);

            statement.setString(COUNT_USERS_WITH_LOGIN_INDEX, login);
            ResultSet set = statement.executeQuery();
            set.next();

            return set.getInt(DAOConstant.COUNT_USERS_WITH_TOTAL);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            List<User> userList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(DAOConstant.FIND_ALL_USERS);
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }

            return userList;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }


    public void registration(User user) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(DAOConstant.ADD_USER);

            statement.setString(ADD_USER_NAME_INDEX, user.getName());
            statement.setString(ADD_USER_SURNAME_INDEX, user.getSurname());
            statement.setString(ADD_USER_PATRONYMIC_INDEX, user.getPatronymic());
            statement.setString(ADD_USER_LOGIN_INDEX, user.getLogin());
            statement.setString(ADD_USER_PASSWORD_INDEX, user.getPassword());
            statement.setString(ADD_USER_PHONE_INDEX, user.getPhoneNumber());
            statement.setString(ADD_USER_EMAIL_INDEX, user.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }

    public User findUserByLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(DAOConstant.FIND_USER_BY_LOGIN);
            statement.setString(DAOConstant.FIND_USER_BY_LOGIN_INDEX, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }


    public User findUserById(int id) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(DAOConstant.FIND_USER_BY_ID);
            statement.setInt(DAOConstant.FIND_USER_BY_ID_ID_INDEX, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }

    @Override
    public void setBanned(int id, boolean condition) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(BAN_USER_BY_ID);

            statement.setBoolean(BAN_USER_BY_ID_CONDITION_INDEX, condition);
            statement.setInt(BAN_USER_BY_ID_ID_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }

    @Override
    public void setRole(int id, Role role) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(CHANGE_ROLE_BY_ID);

            statement.setString(CHANGE_ROLE_BY_ID_ROLE_INDEX, role.name());
            statement.setInt(CHANGE_ROLE_BY_ID_ID_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find driver!", e);
        }
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(DAOConstant.USER_TABLE_NAME);
        String surname = resultSet.getString(DAOConstant.USER_TABLE_SURNAME);
        String patronymic = resultSet.getString(DAOConstant.USER_TABLE_PATRONYMIC);
        String login = resultSet.getString(DAOConstant.USER_TABLE_LOGIN);
        String password = resultSet.getString(DAOConstant.USER_TABLE_PASSWORD);
        boolean isBanned = resultSet.getBoolean(DAOConstant.USER_TABLE_IS_BANNED);
        Role role = Role.valueOf(resultSet.getString(DAOConstant.USER_TABLE_ROLE));
        String phone = resultSet.getString(DAOConstant.USER_TABLE_PHONE);
        String email = resultSet.getString(DAOConstant.USER_TABLE_EMAIL);
        int id = resultSet.getInt(USER_TABLE_ID);
        User user = new User();

        user.setId(id);
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        user.setBanned(isBanned);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }


}
