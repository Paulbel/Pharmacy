package by.pharmacy.dao.impl;

import static by.pharmacy.dao.DAOConstant.*;


public final class SQLManufacturerDAOConstant {
    public final static String ADD_MANUFACTURER =
            "INSERT INTO " + MANUFACTURER_TABLE + " (" + MANUFACTURER_TABLE_COUNTRY_CODE + ", " + MANUFACTURER_TABLE_PHONE_NUMBER + ", " + MANUFACTURER_TABLE_EMAIL + ") " +
                    "VALUES (?,?,?);";

    public final static int ADD_MANUFACTURER_COUNTRY_CODE_INDEX = 1;
    public final static int ADD_MANUFACTURER_PHONE_NUMBER_INDEX = 2;
    public final static int ADD_MANUFACTURER_EMAIL_INDEX = 3;

    public final static String ADD_MANUFACTURER_TRANSLATE =
            "INSERT INTO " + MANUFACTURER_TRANSLATE_TABLE + " (" + MANUFACTURER_TRANSLATE_TABLE_LANGUAGE_CODE + ", " + MANUFACTURER_TRANSLATE_TABLE_MANUFACTURER_ID + ", " + MANUFACTURER_TRANSLATE_TABLE_NAME + ", " + MANUFACTURER_TRANSLATE_TABLE_ADDRESS + ") " +
                    "VALUES (?,?,?,?);";

    public final static int ADD_MANUFACTURER_TRANSLATE_LANGUAGE_CODE_INDEX = 1;
    public final static int ADD_MANUFACTURER_TRANSLATE_MANUFACTURER_ID_INDEX = 2;
    public final static int ADD_MANUFACTURER_TRANSLATE_NAME_INDEX = 3;
    public final static int ADD_MANUFACTURER_TRANSLATE_ADDRESS_INDEX = 4;





    private SQLManufacturerDAOConstant(){}
}
