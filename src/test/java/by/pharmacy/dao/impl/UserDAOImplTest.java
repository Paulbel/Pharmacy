package by.pharmacy.dao.impl;

import by.pharmacy.BaseTest;
import by.pharmacy.entity.Role;
import by.pharmacy.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOImplTest extends BaseTest {

    private List<User> users;

    private static final String ADD_USERS = "INSERT INTO user (login,password,name,surname,patronymic,phone,email) VALUES " +
            "('login8o5vj4mdkd','54157515','Генрих','Синельников','Алексеевич','+375656381587','8o5vj4mdkd@gmail.com'), " +
            "('loginxl5k8m24lx','32880767','Алексей','Гапеенко','Федорович','+375410634274','xl5k8m24lx@gmail.com');";

    private static final String REMOVE_USERS = "DELETE FROM test.user";

    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";



    @Before
    public void testUp() throws Exception {
        this.setUpParameters();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(ADD_USERS);
        }
    }

    private void setUpParameters() {
        this.users = new ArrayList<>();

        User user1 = new User();
        user1.setLogin("login8o5vj4mdkd");
        user1.setPassword("54157515");
        user1.setName("Генрих");
        user1.setSurname("Синельников");
        user1.setPatronymic("Алексеевич");
        user1.setPhoneNumber("+375656381587");
        user1.setEmail("8o5vj4mdkd@gmail.com");
        user1.setRole(Role.USER);

        User user2 = new User();
        user2.setLogin("loginxl5k8m24lx");
        user2.setPassword("32880767");
        user2.setName("Алексей");
        user2.setSurname("Гапеенко");
        user2.setPatronymic("Федорович");
        user2.setPhoneNumber("+375410634274");
        user2.setEmail("xl5k8m24lx@gmail.com");
        user2.setRole(Role.USER);

        User user3 = new User();
        user3.setLogin("login4o47zru1qt");
        user3.setPassword("13718854");
        user3.setName("Евгений");
        user3.setSurname("Артемьев");
        user3.setPatronymic("Афанасьевич");
        user3.setPhoneNumber("+375070676621");
        user3.setEmail("4o47zru1qt@gmail.com");
        user3.setRole(Role.USER);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @After
    public void tearDown() throws Exception {
        this.users = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(REMOVE_USERS);
        }
    }

    @Test
    public void testGetUsers() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();
        assertEquals(users.subList(0,2), sqlUserDAO.getUsers(10, 0));
    }


    @Test
//    @Ignore
    public void testRegistration() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();

        sqlUserDAO.registration(users.get(2));
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, users.get(2).getLogin());
            ResultSet resultSet = statement.executeQuery();

            assertEquals(resultSet.next(),true);

            String name = resultSet.getString("user.name");
            String surname = resultSet.getString("user.surname");
            String patronymic = resultSet.getString("user.patronymic");
            String password = resultSet.getString("user.password");
            String login = resultSet.getString("user.login");
            String role = resultSet.getString("user.role");
            String email = resultSet.getString("user.email");
            String phone = resultSet.getString("user.phone");

            User user = new User();

            user.setName(name);
            user.setRole(Role.valueOf(role.toUpperCase()));
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setPassword(password);
            user.setLogin(login);
            user.setEmail(email);
            user.setPhoneNumber(phone);

            assertEquals(users.get(2),user);
        }

    }

    @Test
//    @Ignore
    public void testFindUserByLogin() throws Exception {
        UserDAOImpl userDAO = new UserDAOImpl();
        User expectedUser = users.get(0);
        User user = userDAO.findUserByLogin(expectedUser.getLogin());
        assertEquals(expectedUser,user);
    }

    @Test
//    @Ignore
    public void testSetRole() throws Exception {
        UserDAOImpl sqlUserDAO = new UserDAOImpl();
        User expectedUser = users.get(0);
        sqlUserDAO.setRole(expectedUser.getLogin(),Role.ADMIN);
        expectedUser.setRole(Role.ADMIN);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, expectedUser.getLogin());
            ResultSet resultSet = statement.executeQuery();

            assertEquals(resultSet.next(),true);

            Role role = Role.valueOf(resultSet.getString("user.role").toUpperCase());

            assertEquals(expectedUser.getRole(),role);
        }

    }

}