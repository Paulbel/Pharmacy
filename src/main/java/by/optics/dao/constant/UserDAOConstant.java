package by.optics.dao.constant;

import static by.optics.dao.constant.CommonDAOConstant.TOTAL;

public final class UserDAOConstant {
    public static final String USERS_TABLE = "user";
    public static final String USERS_TABLE_ID = "id";
    public static final String USERS_TABLE_LOGIN = "login";
    public static final String USERS_TABLE_PASSWORD = "password";
    public static final String USERS_TABLE_NAME = "name";
    public static final String USERS_TABLE_SURNAME = "surname";
    public static final String USERS_TABLE_PATRONYMIC = "patronymic";
    public static final String USERS_TABLE_IS_BANNED = "is_banned";

    public static final String FIND_ALL_USERS =
            "SELECT " +
                    USERS_TABLE_ID + "," +
                    USERS_TABLE_NAME + "," +
                    USERS_TABLE_SURNAME + "," +
                    USERS_TABLE_PATRONYMIC + "," +
                    USERS_TABLE_PASSWORD + "," +
                    USERS_TABLE_LOGIN + " ," +
                    USERS_TABLE_IS_BANNED
                    + " FROM " + UserDAOConstant.USERS_TABLE;


    public static final int BAN_USER_BY_ID_ID_INDEX = 2;
    public static final int BAN_USER_BY_ID_CONDITION_INDEX = 1;
    public static final int BANNED_CONDITION = 1;
    public static final int NOT_BANNED_CONDITION = 1;
    public static final String BAN_USER_BY_ID =
            "UPDATE " + UserDAOConstant.USERS_TABLE +
                    " SET " + USERS_TABLE_IS_BANNED + "=? " +
                    "WHERE " + UserDAOConstant.USERS_TABLE_ID + "=?;";


    public static final String FIND_USER_BY_LOGIN =
            "SELECT " +
                    USERS_TABLE_ID + "," +
                    USERS_TABLE_NAME + "," +
                    USERS_TABLE_SURNAME + "," +
                    USERS_TABLE_PATRONYMIC + "," +
                    USERS_TABLE_PASSWORD + "," +
                    USERS_TABLE_LOGIN + " ," +
                    USERS_TABLE_IS_BANNED +
                    " FROM " + UserDAOConstant.USERS_TABLE
                    + " WHERE " + UserDAOConstant.USERS_TABLE_LOGIN + "=?;";

    public static final int ADD_USER_LOGIN_INDEX = 1;
    public static final int ADD_USER_PASSWORD_INDEX = 2;
    public static final int ADD_USER_NAME_INDEX = 3;
    public static final int ADD_USER_SURNAME_INDEX = 4;
    public static final int ADD_USER_PATRONYMIC_INDEX = 5;
    public static final int ADD_USER_IS_BANNED_INDEX = 7;
    public static final String ADD_USER =
            "INSERT INTO " + USERS_TABLE + " (" +
                    USERS_TABLE_LOGIN + ", " +
                    USERS_TABLE_PASSWORD + ", " +
                    USERS_TABLE_NAME + ", " +
                    USERS_TABLE_SURNAME + ", " +
                    USERS_TABLE_PATRONYMIC + ", " +
                    USERS_TABLE_IS_BANNED +
                    ") " + "VALUES (?,?,?,?,?,?,?);";

    public static final int FIND_USER_BY_LOGIN_INDEX = 1;
    public static final String COUNT_USERS_WITH_LOGIN =
            "SELECT COUNT(*) AS " + TOTAL +
                    "FROM " + USERS_TABLE +
                    " WHERE " + USERS_TABLE_LOGIN + "=?;";
    public static final int COUNT_USERS_WITH_LOGIN_INDEX = 1;

    private UserDAOConstant() {
    }
}
