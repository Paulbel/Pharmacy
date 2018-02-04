package by.pharmacy.dao.impl;

import by.pharmacy.BaseDAOTest;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class UserDAOImplTest extends BaseDAOTest {
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";

    @Test
    public void testGetUsers() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();
        assertEquals(users.subList(0, 2), sqlUserDAO.getUsers(10, 0));
    }


    @Test
//    @Ignore
    public void testRegistration() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();

       // sqlUserDAO.registration(users.get(2));
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, users.get(2).getLogin());
            ResultSet resultSet = statement.executeQuery();

            assertEquals(resultSet.next(), true);

            String name = resultSet.getString("user.name");
            String surname = resultSet.getString("user.surname");
            String password = resultSet.getString("user.password");
            String login = resultSet.getString("user.login");
            String role = resultSet.getString("user.role");
            String email = resultSet.getString("user.email");
            String phone = resultSet.getString("user.phone");

            User user = new User();

            user.setName(name);
            user.setRole(Role.valueOf(role.toUpperCase()));
            user.setSurname(surname);
            //user.setPassword(password);
            user.setLogin(login);
            user.setEmail(email);
            user.setPhoneNumber(phone);

            assertEquals(users.get(2), user);
        }

    }

    @Test
    public void testFindUserByLogin() throws Exception {
        UserDAOImpl userDAO = new UserDAOImpl();
        User expectedUser = users.get(0);
        User user = userDAO.findUserByLogin(expectedUser.getLogin());
        assertEquals(expectedUser, user);
    }

    @Test
    public void testSetRole() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();
        User expectedUser = users.get(0);
        sqlUserDAO.setRole(expectedUser.getLogin(), Role.ADMIN);
        expectedUser.setRole(Role.ADMIN);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, expectedUser.getLogin());
            ResultSet resultSet = statement.executeQuery();

            assertEquals(resultSet.next(), true);

            Role role = Role.valueOf(resultSet.getString("user.role").toUpperCase());

            assertEquals(expectedUser.getRole(), role);
        }

    }

}