package by.optics.dao.constant;


import static by.optics.dao.constant.CommonDAOConstant.TOTAL;

public final class AdminDAOConstant {
    public static final String ADMINS_TABLE_ID = "user_id";
    public static final int COUNT_ADMINS_WITH_ID_ID_INDEX = 1;
    public static final String ADMINS_TABLE = "admin";
    public static final String COUNT_ADMINS_WITH_ID =
            "SELECT COUNT(*) AS \'" + TOTAL + "\' " +
                    "FROM " + ADMINS_TABLE + " WHERE " + ADMINS_TABLE_ID + "=?;";


    private AdminDAOConstant() {
    }
}
