package by.optics.dao.impl;

import by.optics.dao.DAOConsts;
import by.optics.dao.SQLConnectionCreator;
import by.optics.dao.UserDAO;
import by.optics.dao.exception.DAOException;
import by.optics.dao.exception.NoUserFoundException;
import by.optics.entity.Administrator;
import by.optics.entity.Client;
import by.optics.entity.Doctor;
import by.optics.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {
    private final String FIND_USER_WITH_LOGIN = "SELECT * FROM USERS WHERE login =?";
    private final int FIND_USER_WITH_LOGIN_LOGIN_INDEX = 1;
    private final String FIND_USER_IN_ADMINS = "SELECT * FROM ADMINS WHERE id = ?";
    private final int FIND_USER_IN_ADMINS_ID_INDEX = 1;
    private final String FIND_VISITS_OF_DOCTOR = "";
    private final String ADD_USER = "INSERT INTO USERS (" +
            DAOConsts.USERS_TABLE_LOGIN + "," +
            DAOConsts.USERS_TABLE_PASSWORD + ", " +
            DAOConsts.USERS_TABLE_NAME + ", " +
            DAOConsts.USERS_TABLE_SURNAME + ", " +
            DAOConsts.USERS_TABLE_PATRONYMIC + ", " +
            DAOConsts.USERS_TABLE_ISADMIN + ", " +
            DAOConsts.USERS_TABLE_ISDOCTOR + ", " +
            DAOConsts.USERS_TABLE_ISBANNED + ") " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private final int ADD_USER_LOGIN_INDEX = 1;
    private final int ADD_USER_PASSWORD_INDEX = 2;
    private final int ADD_USER_NAME_INDEX = 3;
    private final int ADD_USER_SURNAME_INDEX = 4;
    private final int ADD_USER_PATRONYMIC_INDEX = 5;
    private final int ADD_USER_ISADMIN_INDEX = 6;
    private final int ADD_USER_ISDOCTOR_INDEX = 7;
    private final int ADD_USER_ISBANNED_INDEX = 8;


    public void registration(User user) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_USER);

            statement.setString(ADD_USER_NAME_INDEX, user.getName());
            statement.setString(ADD_USER_SURNAME_INDEX, user.getSurname());
            statement.setString(ADD_USER_PATRONYMIC_INDEX,user.getPatronymic());
            statement.setString(ADD_USER_LOGIN_INDEX,user.getLogin());
            statement.setString(ADD_USER_PASSWORD_INDEX,user.getPassword());
            statement.setBoolean(ADD_USER_ISADMIN_INDEX,false);
            statement.setBoolean(ADD_USER_ISDOCTOR_INDEX,false);
            statement.setBoolean(ADD_USER_ISBANNED_INDEX,false);

            statement.executeUpdate();
        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }

    public User findUser(int id) {
        return null;
    }

    public User findUser(String name, String surname, String patronymic) {
        return null;
    }

    public User findUser(String login) throws DAOException {
        User user = null;
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_WITH_LOGIN);
            int id;
            statement.setString(FIND_USER_WITH_LOGIN_LOGIN_INDEX, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                throw new NoUserFoundException();
            }
            resultSet.next();
            id = resultSet.getInt(DAOConsts.USERS_TABLE_ID);
            String name = resultSet.getString(DAOConsts.USERS_TABLE_NAME);
            String surname = resultSet.getString(DAOConsts.USERS_TABLE_SURNAME);
            String patronymic = resultSet.getString(DAOConsts.USERS_TABLE_PATRONYMIC);
            String password = resultSet.getString(DAOConsts.USERS_TABLE_PASSWORD);
            boolean isBanned = resultSet.getBoolean(DAOConsts.USERS_TABLE_ISBANNED);
            boolean isAdmin = resultSet.getBoolean(DAOConsts.USERS_TABLE_ISADMIN);
            boolean isDoctor = resultSet.getBoolean(DAOConsts.USERS_TABLE_ISDOCTOR);

            if (isAdmin) {
                Administrator administrator = new Administrator();
                //TODO admin logic
                user = administrator;
            } else if (isDoctor) {
                Doctor doctor = new Doctor();
                //TODO doctor creation Logic
                user = doctor;

            } else {
                Client client = new Client();
                if (isBanned) {
                    client.setBanned(true);
                }
                user = client;
            }
            user.setLogin(login);
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setPassword(password);
        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
        return user;
    }


}
