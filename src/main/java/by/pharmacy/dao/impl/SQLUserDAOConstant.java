package by.pharmacy.dao.impl;


import static by.pharmacy.dao.DAOConstant.*;

public final class SQLUserDAOConstant {

    public static final String USER_TABLE_COLUMNS =
            USER_TABLE_NAME + "," +
                    USER_TABLE_SURNAME + "," +
                    USER_TABLE_PATRONYMIC + "," +
                    USER_TABLE_PASSWORD + "," +
                    USER_TABLE_LOGIN + " ," +
                    USER_TABLE_ROLE + "," +
                    USER_TABLE_PHONE + ", " +
                    USER_TABLE_EMAIL;


    public static final String GET_USERS =
            "SELECT " + USER_TABLE_COLUMNS +
                    " FROM " + USER_TABLE + " LIMIT ? OFFSET ?";

    public static final int GET_USERS_START_INDEX = 1;
    public static final int GET_USERS_OFFSET_INDEX = 2;

    public static final String FIND_USER_BY_LOGIN =
            "SELECT " + USER_TABLE_COLUMNS +
                    " FROM " + USER_TABLE +
                    " WHERE " + USER_TABLE_LOGIN + "=?;";




    public static final int FIND_USER_BY_ID_ID_INDEX = 1;
    public static final int ADD_USER_LOGIN_INDEX = 1;
    public static final int ADD_USER_PASSWORD_INDEX = 2;
    public static final int ADD_USER_NAME_INDEX = 3;
    public static final int ADD_USER_SURNAME_INDEX = 4;
    public static final int ADD_USER_PATRONYMIC_INDEX = 5;
    public static final int ADD_USER_PHONE_INDEX = 6;
    public static final int ADD_USER_EMAIL_INDEX = 7;

    public static final String ADD_USER =
            "INSERT INTO " + USER_TABLE + " (" +
                    USER_TABLE_LOGIN + ", " +
                    USER_TABLE_PASSWORD + ", " +
                    USER_TABLE_NAME + ", " +
                    USER_TABLE_SURNAME + ", " +
                    USER_TABLE_PATRONYMIC + ", " +
                    USER_TABLE_PHONE + "," +
                    USER_TABLE_EMAIL +
                    ") " + "VALUES (?,?,?,?,?,?,?);";

    public static final int FIND_USER_BY_LOGIN_INDEX = 1;



    public static final String CHANGE_ROLE_BY_LOGIN =
            "UPDATE " + USER_TABLE +
                    " SET " + USER_TABLE_ROLE + "=? " +
                    "WHERE " + USER_TABLE_LOGIN + "=?;";

    public static final int CHANGE_ROLE_BY_LOGIN_LOGIN_INDEX = 2;
    public static final int CHANGE_ROLE_BY_LOGIN_ROLE_INDEX = 1;


    private SQLUserDAOConstant() {
    }
}
