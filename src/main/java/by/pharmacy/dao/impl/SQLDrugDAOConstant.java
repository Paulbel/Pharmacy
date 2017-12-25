package by.pharmacy.dao.impl;

import static by.pharmacy.dao.DAOConstant.*;

public final class SQLDrugDAOConstant {



    public static final String ADD_DRUG =
            "INSERT INTO " + DRUG_TABLE + " (" +
                    DRUG_TABLE_MANUFACTURER_ID + ", " +
                    DRUG_TABLE_DOSAGE + ", " +
                    DRUG_TABLE_AMOUNT + ", " +
                    DRUG_TABLE_PRICE + ", " +
                    DRUG_TABLE_NUMBER + ", " +
                    DRUG_TABLE_NEED_PRESCRIPTION +
                    ") VALUES (?,?,?,?,?,?)";
    public static final int ADD_DRUG_MANUFACTURER_ID_INDEX = 1;
    public static final int ADD_DRUG_DOSAGE_INDEX = 2;
    public static final int ADD_DRUG_AMOUNT_INDEX = 3;
    public static final int ADD_DRUG_PRICE_INDEX = 4;
    public static final int ADD_DRUG_NUMBER_INDEX = 5;
    public static final int ADD_DRUG_NEED_PRESCRIPTION_INDEX = 6;









    public static final String ADD_DRUG_DESCRIPTION =
            "INSERT INTO " + DRUG_TRANSLATE_TABLE + " (" +
                    DRUG_TRANSLATE_TABLE_LANG_CODE + ", " +
                    DRUG_TRANSLATE_TABLE_NAME + ", " +
                    DRUG_TRANSLATE_TABLE_DESCRIPTION + ", " +
                    DRUG_TRANSLATE_TABLE_COMPOSITION + "," +
                    DRUG_TRANSLATE_TABLE_DRUG_ID +
                    ") VALUES (?,?,?,?,?)";

    public final static int ADD_DRUG_DESCRIPTION_LANG_CODE_INDEX = 1;
    public final static int ADD_DRUG_DESCRIPTION_NAME_INDEX = 2;
    public final static int ADD_DRUG_DESCRIPTION_DESCRIPTION_INDEX = 3;
    public final static int ADD_DRUG_DESCRIPTION_COMPOSITION_INDEX = 4;
    public final static int ADD_DRUG_DESCRIPTION_DRUG_ID_INDEX = 5;

    public final static String DRUG_COLUMNS =
            DRUG_TABLE_ID + ", " +
                    DRUG_TRANSLATE_TABLE_NAME + ", " +
                    DRUG_TRANSLATE_TABLE_COMPOSITION + ", " +
                    DRUG_TABLE_NUMBER + ", " +
                    DRUG_TABLE_AMOUNT + ", " +
                    DRUG_TABLE_DOSAGE + ", " +
                    DRUG_TRANSLATE_TABLE_DESCRIPTION + ", " +
                    DRUG_TABLE_NEED_PRESCRIPTION + ", " +
                    DRUG_TABLE_PRICE + ", " +
                    MANUFACTURER_TABLE_ID + ", " +
                    MANUFACTURER_TABLE_PHONE_NUMBER + ", " +
                    MANUFACTURER_TRANSLATE_TABLE_NAME;



    public final static String FIND_DRUG =
            "SELECT " + DRUG_COLUMNS + " " +
                    "FROM " + DRUG_TRANSLATE_TABLE + " " +
                    "INNER JOIN " + DRUG_TABLE + " ON " + DRUG_TRANSLATE_TABLE_DRUG_ID + " = " + DRUG_TABLE_ID + " " +
                    "INNER JOIN " + MANUFACTURER_TABLE + " ON " + DRUG_TABLE_MANUFACTURER_ID + " = " + MANUFACTURER_TABLE_ID + " " +
                    "INNER JOIN " + MANUFACTURER_TRANSLATE_TABLE + " ON " + MANUFACTURER_TABLE_ID + " = " + MANUFACTURER_TRANSLATE_TABLE_MANUFACTURER_ID + " " +
                    "WHERE " + DRUG_TRANSLATE_TABLE_NAME + " LIKE ?;";
    public final static int FIND_DRUG_NAME_INDEX = 1;










    private SQLDrugDAOConstant() {
    }
}
