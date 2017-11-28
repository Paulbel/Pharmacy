package by.optics.dao;

public final class DAOConstant {
    public static final String USER_TABLE = "user";
    public static final String USER_TABLE_ID = "id";
    public static final String USER_TABLE_LOGIN = "login";
    public static final String USER_TABLE_PASSWORD = "password";
    public static final String USER_TABLE_NAME = "name";
    public static final String USER_TABLE_SURNAME = "surname";
    public static final String USER_TABLE_PATRONYMIC = "patronymic";
    public static final String USER_TABLE_ROLE = "role";
    public static final String USER_TABLE_IS_BANNED = "is_banned";
    public static final String USER_TABLE_PHONE = "pnone";
    public static final String USER_TABLE_EMAIL = "email";
    public static final String USER_TABLE_COLUMNS =
            USER_TABLE_NAME + "," +
            USER_TABLE_ID + "," +
            USER_TABLE_SURNAME + "," +
            USER_TABLE_PATRONYMIC + "," +
            USER_TABLE_PASSWORD + "," +
            USER_TABLE_LOGIN + " ," +
            USER_TABLE_ROLE + "," +
            USER_TABLE_IS_BANNED + ", " +
            USER_TABLE_PHONE + ", " +
            USER_TABLE_EMAIL;


    public static final String FIND_ALL_USERS =
            "SELECT " + USER_TABLE_COLUMNS +
                    " FROM " + USER_TABLE;

    public static final String FIND_USER_BY_LOGIN =
            "SELECT " + USER_TABLE_COLUMNS +
                    " FROM " + USER_TABLE +
                    " WHERE " + USER_TABLE_LOGIN + "=?;";


    public static final String FIND_USER_BY_ID =
            "SELECT " + USER_TABLE_COLUMNS +
                    " FROM " + USER_TABLE +
                    " WHERE " + USER_TABLE_ID + "=?;";

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
    public static final String COUNT_USERS_WITH_TOTAL = "total";
    public static final int FIND_USER_BY_LOGIN_INDEX = 1;
    public static final String COUNT_USERS_WITH_LOGIN =
            "SELECT COUNT(*) AS \'" + COUNT_USERS_WITH_TOTAL + "\' " +
                    "FROM " + USER_TABLE + " " +
                    "WHERE login=?;";
    public static final int COUNT_USERS_WITH_LOGIN_INDEX = 1;


    public static final int BAN_USER_BY_ID_ID_INDEX = 2;
    public static final int BAN_USER_BY_ID_CONDITION_INDEX = 1;
    public static final int BANNED_CONDITION = 1;
    public static final int NOT_BANNED_CONDITION = 0;

    public static final String BAN_USER_BY_ID =
            "UPDATE " + USER_TABLE +
                    " SET " + USER_TABLE_IS_BANNED + "=? " +
                    "WHERE " + USER_TABLE_ID + "=?;";

    public static final String CHANGE_ROLE_BY_ID =
            "UPDATE " + USER_TABLE +
                    " SET " + USER_TABLE_ROLE + "=? " +
                    "WHERE " + USER_TABLE_ID + "=?;";

    public static final int CHANGE_ROLE_BY_ID_ID_INDEX = 2;
    public static final int CHANGE_ROLE_BY_ID_ROLE_INDEX = 1;
    private DAOConstant() {
    }
}
