package by.pharmacy.dao.impl;

import by.pharmacy.BaseDAOTest;
import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.UserDAO;
import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.entity.User;
import by.pharmacy.entity.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class UserDAOImplTest extends BaseDAOTest {
    private final static UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final static String ADD_USERS = "INSERT INTO user (login, password, name, surname, phone, email) VALUES " +
            "('login1', MD5('password1'), 'name1', 'surname1', 'phone1', 'email1')," +
            "('login2', MD5('password2'), 'name2', 'surname2', 'phone2', 'email2'), " +
            "('login3', MD5('password3'), 'name3', 'surname3', 'phone3', 'email3'), " +
            "('login4', MD5('password4'), 'name4', 'surname4', 'phone4', 'email4')," +
            "('login5', MD5('password5'), 'name5', 'surname5', 'phone5', 'email5')";

    private final static String SIGN_UP = "('login1', MD5('password1'), 'name1', 'surname1', 'phone1', 'email1')";

    private final static String GET_USER = "SELECT * FROM user WHERE login = ?;";

    private final static String REMOVE_USERS = "DELETE FROM user";

    @Before
    public void setUp() throws Exception {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(ADD_USERS);
        }
    }

    @After
    public void tearDown() throws Exception {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(REMOVE_USERS);
        }
    }

    @Test
    public void getUserList() throws Exception {
        assertEquals(userList.subList(0, 5), userDAO.getUserList(10, 0));
    }

    @Test
    public void signUpTestValidParameters() throws Exception {
        User expectedUser = userList.get(5);
        userDAO.signUp(expectedUser, "password6");
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, userList.get(5).getLogin());
            ResultSet resultSet = statement.executeQuery();
            assertEquals(true, resultSet.next());
            User user = new User();
            user.setName(resultSet.getString("user.name"));
            user.setSurname(resultSet.getString("user.surname"));
            user.setLogin(resultSet.getString("user.login"));
            user.setRole(UserRole.valueOf(resultSet.getString("user.role").toUpperCase()));
            user.setPhoneNumber(resultSet.getString("user.phone"));
            user.setEmail(resultSet.getString("user.email"));
            assertEquals(expectedUser, user);
        }
    }

    @Test(expected = DAOException.class)
    public void signUpTestUserExists() throws Exception {
        User expectedUser = userList.get(0);
        userDAO.signUp(expectedUser, "password6");
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, userList.get(0).getLogin());
            ResultSet resultSet = statement.executeQuery();
            assertEquals(true, resultSet.next());
            assertEquals(expectedUser, createUser(resultSet));
        }
    }

    @Test
    public void findUserByLogin() throws Exception {
        User user = userDAO.findUserByLogin(userList.get(0).getLogin());
        assertEquals(userList.get(0), user);
    }

    @Test
    public void setRole() throws Exception {
        User expectedUser = userList.get(0);
        userDAO.setRole(expectedUser.getLogin(), UserRole.ADMIN);
        expectedUser.setRole(UserRole.ADMIN);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, userList.get(0).getLogin());
            ResultSet resultSet = statement.executeQuery();
            assertEquals(true, resultSet.next());
            assertEquals(expectedUser, createUser(resultSet));
        }
    }

    @Test
    public void signIn() throws Exception {
        User expectedUser = userList.get(0);
        User user = userDAO.signIn(expectedUser.getLogin(), "password1");
        assertEquals(expectedUser, user);
    }


    private User createUser(ResultSet resultSet) throws SQLException {
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