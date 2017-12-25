package by.pharmacy.dao;

public final class DAOConstant {
    public final static String CURRENT_DB = "test";
    public final static String DRIVER_NAME = "driver";
    public final static String URL = "url";
    public final static String USER = "user";
    public final static String PASSWORD = "password";
    public final static String POOL_SIZE = "pool_size";

    public static final String USER_TABLE = "user";
    public static final String USER_TABLE_LOGIN = USER_TABLE + ".login";
    public static final String USER_TABLE_PASSWORD = USER_TABLE + ".password";
    public static final String USER_TABLE_NAME = USER_TABLE + ".name";
    public static final String USER_TABLE_SURNAME = USER_TABLE + ".surname";
    public static final String USER_TABLE_PATRONYMIC = USER_TABLE + ".patronymic";
    public static final String USER_TABLE_ROLE = USER_TABLE + ".role";
    public static final String USER_TABLE_PHONE = USER_TABLE + ".phone";
    public static final String USER_TABLE_EMAIL = USER_TABLE + ".email";

    public final static String COUNTRY_TABLE = "country";
    public final static String COUNTRY_TABLE_CODE = COUNTRY_TABLE + ".code";

    public final static String COUNTRY_TRANSLATE_TABLE = "country_translate";
    public final static String COUNTRY_TRANSLATE_TABLE_COUNTRY_CODE = COUNTRY_TRANSLATE_TABLE + ".country_code";
    public final static String COUNTRY_TRANSLATE_TABLE_LANGUAGE_CODE = COUNTRY_TRANSLATE_TABLE + ".lan_code";
    public final static String COUNTRY_TRANSLATE_TABLE_NAME = COUNTRY_TRANSLATE_TABLE + ".name";


    public final static String GET_COUNTRY_CODE =
            "SELECT " + COUNTRY_TRANSLATE_TABLE_COUNTRY_CODE +
                    " FROM " + COUNTRY_TRANSLATE_TABLE +
                    " WHERE " + COUNTRY_TRANSLATE_TABLE_LANGUAGE_CODE + " = ? AND " + COUNTRY_TRANSLATE_TABLE_NAME + " = ?";

    public final static int GET_COUNTRY_CODE_LANGUAGE_CODE_INDEX = 1;
    public final static int GET_COUNTRY_CODE_NAME_INDEX = 2;


    public final static String MANUFACTURER_TABLE = "manufacturer";
    public final static String MANUFACTURER_TABLE_ID = MANUFACTURER_TABLE + ".id";
    public final static String MANUFACTURER_TABLE_COUNTRY_CODE = MANUFACTURER_TABLE + ".country_code";
    public final static String MANUFACTURER_TABLE_PHONE_NUMBER = MANUFACTURER_TABLE + ".phone_number";
    public final static String MANUFACTURER_TABLE_EMAIL = MANUFACTURER_TABLE + ".email";

    public final static String MANUFACTURER_TRANSLATE_TABLE = "manufacturer_translate";
    public final static String MANUFACTURER_TRANSLATE_TABLE_LANGUAGE_CODE = MANUFACTURER_TRANSLATE_TABLE + ".language_code";
    public final static String MANUFACTURER_TRANSLATE_TABLE_MANUFACTURER_ID = MANUFACTURER_TRANSLATE_TABLE + ".manufacturer_id";
    public final static String MANUFACTURER_TRANSLATE_TABLE_NAME = MANUFACTURER_TRANSLATE_TABLE + ".name";
    public final static String MANUFACTURER_TRANSLATE_TABLE_ADDRESS = MANUFACTURER_TRANSLATE_TABLE + ".address";

    public final static String DRUG_TABLE = "drug";
    public final static String DRUG_TABLE_ID = DRUG_TABLE + ".id";
    public final static String DRUG_TABLE_MANUFACTURER_ID = DRUG_TABLE + ".manufacturer_id";
    public final static String DRUG_TABLE_DOSAGE = DRUG_TABLE + ".dosage";
    public final static String DRUG_TABLE_AMOUNT = DRUG_TABLE + ".amount";
    public final static String DRUG_TABLE_PRICE = DRUG_TABLE + ".price";
    public final static String DRUG_TABLE_NUMBER = DRUG_TABLE + ".number";
    public final static String DRUG_TABLE_NEED_PRESCRIPTION = DRUG_TABLE + ".need_prescription";

    public final static String DRUG_TRANSLATE_TABLE = "drug_translate";
    public final static String DRUG_TRANSLATE_TABLE_DRUG_ID = DRUG_TRANSLATE_TABLE + ".drug_id";
    public final static String DRUG_TRANSLATE_TABLE_LANG_CODE = DRUG_TRANSLATE_TABLE + ".lang_code";
    public final static String DRUG_TRANSLATE_TABLE_NAME = DRUG_TRANSLATE_TABLE + ".name";
    public final static String DRUG_TRANSLATE_TABLE_DESCRIPTION = DRUG_TRANSLATE_TABLE + ".description";
    public final static String DRUG_TRANSLATE_TABLE_COMPOSITION = DRUG_TRANSLATE_TABLE + ".composition";


    public static final int FIND_LANGUAGE_CODE_BY_NAME_NAME_INDEX = 1;

    public final static String LANGUAGE_TABLE = "language";
    public final static String LANGUAGE_TABLE_CODE = LANGUAGE_TABLE + ".language_code";
    public final static String LANGUAGE_TABLE_NAME = LANGUAGE_TABLE + ".name";


    public static final String FIND_LANGUAGE_CODE_BY_NAME =
            "SELECT " + LANGUAGE_TABLE_CODE +
                    " FROM " + LANGUAGE_TABLE +
                    " WHERE " + LANGUAGE_TABLE_NAME + "=?";

    private DAOConstant() {
    }
}
