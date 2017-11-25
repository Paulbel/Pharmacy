package by.optics.dao.impl;

import by.optics.dao.DAOConstant;
import by.optics.dao.UserRole;
import by.optics.dao.SQLConnectionCreator;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.Administrator;
import by.optics.entity.user.Client;
import by.optics.entity.user.Doctor;
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
        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
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
        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
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
            statement.setBoolean(ADD_USER_IS_BANNED_INDEX, user.isBanned());
            statement.setString(ADD_USER_PHONE_INDEX, user.getPhoneNumber());
            statement.setString(ADD_USER_ROLE_INDEX, String.valueOf(UserRole.USER));
            statement.setString(ADD_USER_EMAIL_INDEX, user.getEmail());
            statement.executeUpdate();

        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }

    public User findUserByLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(DAOConstant.FIND_USER_BY_LOGIN);
            statement.setString(DAOConstant.FIND_USER_BY_LOGIN_INDEX, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(DAOConstant.USERS_TABLE_NAME);
        String surname = resultSet.getString(DAOConstant.USERS_TABLE_SURNAME);
        String patronymic = resultSet.getString(DAOConstant.USERS_TABLE_PATRONYMIC);
        String login = resultSet.getString(DAOConstant.USERS_TABLE_LOGIN);
        String password = resultSet.getString(DAOConstant.USERS_TABLE_PASSWORD);
        boolean isBanned = resultSet.getBoolean(DAOConstant.USERS_TABLE_IS_BANNED);
        UserRole role = UserRole.valueOf(resultSet.getString(DAOConstant.USERS_TABLE_ROLE));
        String phone = resultSet.getString(DAOConstant.USERS_TABLE_PHONE);
        String email = resultSet.getString(DAOConstant.USERS_TABLE_EMAIL);
        User user = createUserWithSelectedRole(role);

        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        user.setBanned(isBanned);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        return user;
    }

    private User createUserWithSelectedRole(UserRole role) {
        User user = null;
        switch (role) {
            case ADMIN:
                user = new Administrator();
                break;
            case CLIENT:
                user = new Client();
                break;
            case DOCTOR:
                user = new Doctor();
                break;
            case USER:
                user = new User();
                break;
        }
        return user;
    }

}
