package by.optics.dao.impl;

import by.optics.dao.AdminDAO;
import by.optics.dao.SQLConnectionCreator;
import by.optics.dao.constant.CommonDAOConstant;
import by.optics.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.optics.dao.constant.AdminDAOConstant.COUNT_ADMINS_WITH_ID;
import static by.optics.dao.constant.AdminDAOConstant.COUNT_ADMINS_WITH_ID_ID_INDEX;

public class SQLAdminDAO implements AdminDAO {
    public boolean userIsAdmin(int id) throws DAOException {
        try (Connection connection = SQLConnectionCreator.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(COUNT_ADMINS_WITH_ID);

            statement.setInt(COUNT_ADMINS_WITH_ID_ID_INDEX, id);
            ResultSet set = statement.executeQuery();
            set.next();
            int number = set.getInt(CommonDAOConstant.TOTAL);
            if (number == 0) {
                return false;
            }
            return true;

        } catch (SQLException |
                ClassNotFoundException e) {
            throw new DAOException("Cannot connect to database!", e);
        }
    }
}
