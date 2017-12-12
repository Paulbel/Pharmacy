package by.pharmacy.dao.impl;

import by.pharmacy.dao.SQLConnectionCreator;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.pharmacy.dao.impl.SQLUserDAOConstant.*;

public class SQLUserDAO implements UserDAO {
    public int findNumberOfUsersWithLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.COUNT_USERS_WITH_LOGIN);

            statement.setString(COUNT_USERS_WITH_LOGIN_INDEX, login);
            ResultSet set = statement.executeQuery();
            set.next();

            return set.getInt(SQLUserDAOConstant.COUNT_USERS_WITH_TOTAL);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            List<User> userList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(SQLUserDAOConstant.FIND_ALL_USERS);
            while (set.next()) {
                userList.add(createUserFromResultSet(set));
            }

            return userList;
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }


    public void registration(User user) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.ADD_USER);

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
        }
    }

    public User findUserByLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.FIND_USER_BY_LOGIN);
            statement.setString(SQLUserDAOConstant.FIND_USER_BY_LOGIN_INDEX, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }


    public User findUserById(int id) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLUserDAOConstant.FIND_USER_BY_ID);
            statement.setInt(SQLUserDAOConstant.FIND_USER_BY_ID_ID_INDEX, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
                return createUserFromResultSet(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Cannot connect to database!", e);
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
        }
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(SQLUserDAOConstant.USER_TABLE_NAME);
        String surname = resultSet.getString(SQLUserDAOConstant.USER_TABLE_SURNAME);
        String patronymic = resultSet.getString(SQLUserDAOConstant.USER_TABLE_PATRONYMIC);
        String login = resultSet.getString(SQLUserDAOConstant.USER_TABLE_LOGIN);
        String password = resultSet.getString(SQLUserDAOConstant.USER_TABLE_PASSWORD);
        Role role = Role.valueOf(resultSet.getString(SQLUserDAOConstant.USER_TABLE_ROLE).toUpperCase());
        String phone = resultSet.getString(SQLUserDAOConstant.USER_TABLE_PHONE);
        String email = resultSet.getString(SQLUserDAOConstant.USER_TABLE_EMAIL);
        int id = resultSet.getInt(USER_TABLE_ID);
        User user = new User();

        user.setId(id);
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
