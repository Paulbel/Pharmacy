package by.optics.dao;

import by.optics.dao.exception.DAOException;

public interface AdminDAO {
    public boolean userIsAdmin(int id) throws DAOException;
}
