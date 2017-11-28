package by.optics.dao.impl;

import by.optics.dao.SQLConnectionCreator;
import by.optics.dao.UserDAO;
import by.optics.dao.constant.CommonDAOConstant;
import by.optics.dao.constant.UserDAOConstant;
import by.optics.dao.exception.DAOException;
import by.optics.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.optics.dao.constant.UserDAOConstant.*;

public class SQLUserDAO implements UserDAO {

    public int findNumberOfUsersWithLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(UserDAOConstant.COUNT_USERS_WITH_LOGIN);

            statement.setString(COUNT_USERS_WITH_LOGIN_INDEX, login);
            ResultSet set = statement.executeQuery();
            set.next();

            return set.getInt(CommonDAOConstant.TOTAL);
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
            ResultSet set = statement.executeQuery(UserDAOConstant.FIND_ALL_USERS);
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
            PreparedStatement statement = connection.prepareStatement(UserDAOConstant.ADD_USER);

            statement.setString(ADD_USER_NAME_INDEX, user.getName());
            statement.setString(ADD_USER_SURNAME_INDEX, user.getSurname());
            statement.setString(ADD_USER_PATRONYMIC_INDEX, user.getPatronymic());
            statement.setString(ADD_USER_LOGIN_INDEX, user.getLogin());
            statement.setString(ADD_USER_PASSWORD_INDEX, user.getPassword());
            statement.setBoolean(ADD_USER_IS_BANNED_INDEX, user.isBanned());
            statement.executeUpdate();

        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }

    public User findUserByLogin(String login) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(UserDAOConstant.FIND_USER_BY_LOGIN);
            statement.setString(FIND_USER_BY_LOGIN_INDEX, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createUserFromResultSet(resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }


    public void setBanned(int id, boolean ban) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(BAN_USER_BY_ID);

            statement.setBoolean(BAN_USER_BY_ID_CONDITION_INDEX, ban);
            statement.setInt(BAN_USER_BY_ID_ID_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }


    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(USERS_TABLE_NAME);
        String surname = resultSet.getString(USERS_TABLE_SURNAME);
        String patronymic = resultSet.getString(USERS_TABLE_PATRONYMIC);
        String login = resultSet.getString(USERS_TABLE_LOGIN);
        String password = resultSet.getString(USERS_TABLE_PASSWORD);
        boolean isBanned = resultSet.getBoolean(USERS_TABLE_IS_BANNED);
        int id = resultSet.getInt(USERS_TABLE_ID);

        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        user.setBanned(isBanned);
        return user;
    }


}
