package by.pharmacy.dao.impl;

public final class SQLDrugDAOConstant {


    public final static String DRUG_TABLE = "drug";
    public final static String DRUG_TABLE_ID = DRUG_TABLE+".id";
    public final static String DRUG_TABLE_MANUFACTURER_ID = DRUG_TABLE+".manufacurer_id";
    public final static String DRUG_TABLE_DOSAGE = DRUG_TABLE+".dosage";
    public final static String DRUG_TABLE_AMOUNT = DRUG_TABLE+".amount";
    public final static String DRUG_TABLE_PRICE = DRUG_TABLE+".price";
    public final static String DRUG_TABLE_NUMBER = DRUG_TABLE+".number";
    public final static String DRUG_TABLE_NEED_PRESCRIPTION = DRUG_TABLE+".need_presciption";
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


    public final static String LANGUAGE_TABLE = "language";
    public final static String LANGUAGE_TABLE_CODE = LANGUAGE_TABLE+".code";
    public final static String LANGUAGE_TABLE_NAME = LANGUAGE_TABLE+".name";

    public static final String FIND_LANGUAGE_CODE_BY_NAME =
            "SELECT " + LANGUAGE_TABLE_CODE +
                    " FROM " + LANGUAGE_TABLE +
                    " WHERE " + LANGUAGE_TABLE_NAME + "=?";

    public static final int FIND_LANGUAGE_CODE_BY_NAME_NAME_INDEX = 1;


    public final static String DRUG_TRANSLATE_TABLE = "drug_translate";
    public final static String DRUG_TRANSLATE_TABLE_DRUG_ID = DRUG_TRANSLATE_TABLE+".drug_id";
    public final static String DRUG_TRANSLATE_TABLE_LANG_CODE = DRUG_TRANSLATE_TABLE+".lang_code";
    public final static String DRUG_TRANSLATE_TABLE_NAME = DRUG_TRANSLATE_TABLE+".name";
    public final static String DRUG_TRANSLATE_TABLE_DESCRIPTION = DRUG_TRANSLATE_TABLE+".description";
    public final static String DRUG_TRANSLATE_TABLE_COMPOSITION = DRUG_TRANSLATE_TABLE+".composition";

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


    public final static String GET_ALL_DRUG =
            "SELECT "+
    DRUG_TABLE_ID +", "+
    DRUG_TRANSLATE_TABLE_NAME +", "+
    DRUG_TRANSLATE_TABLE_COMPOSITION +", "+
    DRUG_TABLE_NUMBER +", "+
    DRUG_TABLE_AMOUNT +", "+
    DRUG_TABLE_DOSAGE +", "+
    DRUG_TRANSLATE_TABLE_DESCRIPTION +", "+
    DRUG_TABLE_NEED_PRESCRIPTION +", "+
    DRUG_TABLE_PRICE +", "+
    "manufacturer.id"+ ", "+
    "manufacturer.phone_number" +", "+
    "manufacturer_translate.name"+
    " FROM " +DRUG_TABLE+
    " INNER JOIN "+DRUG_TRANSLATE_TABLE+" ON "+DRUG_TABLE_ID+" = "+DRUG_TRANSLATE_TABLE_DRUG_ID+
    " INNER JOIN "+"manufacturer"+" ON "+"drug.manufacurer_id"+" = "+"manufacturer.id"+
    " INNER JOIN "+"country"+" ON "+"manufacturer.country_id"+ " = "+"country.id"+
                    " INNER JOIN "+"manufacturer_translate"+" ON "+"manufacturer.id"+ " = "+"manufacturer_translate.manufacturer_id"+
                    " INNER JOIN "+"language"+" ON "+"drug_translate.lang_code"+ " = "+"language.code"+
    " WHERE "+"language.name"+"=?"+" AND "+"drug_translate.lang_code"+"="+"manufacturer_translate.language_code";
    public final static int GET_ALL_DRUG_LANGUAGE_INDEX = 1;




    private SQLDrugDAOConstant() {
    }
}
