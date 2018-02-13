package by.pharmacy.controller;

public final class ControllerConstant {
    public static final String LOGIN_ATTRIBUTE = "login";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String SURNAME_ATTRIBUTE = "surname";
    public static final String PHONE_ATTRIBUTE = "phone";
    public static final String EMAIL_ATTRIBUTE = "email";
    public static final String LOCAL_ATTRIBUTE = "local";
    public static final String USERS_ATTRIBUTE = "users";
    public static final String USER_ATTRIBUTE = "user";
    public static final String COMMAND_ATTRIBUTE = "command";
    public static final String PREV_COMMAND_ATTRIBUTE = "prev_command";
    public static final String CURRENT_OPTION_ATTRIBUTE = "want_command";
    public static final String PROBLEM_DESCRIPTION = "problem_description";
    public static final String WRONG_DATA = "wrong_data";

    public static final String DAY_COUNT_ATTRIBUTE = "day_count";

    public static final String MANUFACTURER_ATTRIBUTE = "manufacturer";
    public static final String MANUFACTURER_LIST_ATTRIBUTE = "manufacturer_list";
    public static final String MANUFACTURER_ID_ATTRIBUTE = "manufacturer_id";
    public static final String MANUFACTURER_ADDRESS_ATTRIBUTE = "manufacturer_address";
    public static final String MANUFACTURER_NAME_ATTRIBUTE = "manufacturer_name";

    public static final String ORDER_LIST_ATTRIBUTE = "order_list";


    public static final String COUNTRY_CODE_ATTRIBUTE = "country_code";

    public static final String DRUG_ATTRIBUTE = "drug";
    public static final String DRUGS_ATTRIBUTE = "drugs";
    public static final String DRUG_ID_ATTRIBUTE = "drug_id";
    public static final String DRUG_NAME_ATTRIBUTE = "drug_name";
    public static final String DRUG_DESCRIPTION_ATTRIBUTE = "drug_description";
    public static final String DRUG_COMPOSITION_ATTRIBUTE = "drug_composition";
    public static final String DRUG_AMOUNT_ATTRIBUTE = "drug_amount";
    public static final String DRUG_DOSAGE_ATTRIBUTE = "drug_dosage";
    public static final String DRUG_NEED_PRESCRIPTION_ATTRIBUTE = "need_prescription";
    public static final String DRUG_PRICE_ATTRIBUTE = "drug_price";
    public static final String DRUG_NUMBER_ATTRIBUTE = "drug_number";
    public static final String LANGUAGES_ATTRIBUTE = "languages";
    public static final String COUNTRY_LIST_ATTRIBUTE = "country_list";
    public static final String PRESCRIPTION_LIST_ATTRIBUTE = "prescription_list";

    public static final String ROLE_ATTRIBUTE = "role";

    public static final String MAIN_PAGE_URI = "index.jsp";


    public static final String GET_COUNTRY_LIST_COMMAND = "get_country_list";
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_OUT_COMMAND = "sign_out";
    public static final String ADD_DESCRIPTION_COMMAND = "add_drug_description";
    public static final String GET_MANUFACTURER_COMMAND = "get_manufacturer";
    public static final String CHANGE_DRUG_DESCRIPTION_COMMAND = "change_drug_description";
    public static final String CHANGE_DRUG_INFO_COMMAND = "change_drug_info";
    public static final String GET_MANUFACTURER_LIST_COMMAND = "get_manufacturer_list";
    public static final String ADD_MANUFACTURER_COMMAND = "add_manufacturer";
    public static final String ADD_PRESCRIPTION_COMMAND = "add_prescription";
    public static final String CHANGE_LANGUAGE_COMMAND = "change_language";
    public static final String GET_LANGUAGE_AND_DRUGS_COMMAND = "get_language_drugs";
    public static final String GIVE_ROLE_COMMAND = "give_role";


    public static final String PHARMACIST_CABINET_URI = "WEB-INF/jsp/pharmacist_cabinet.jsp";
    public static final String ADMIN_CABINET_URI = "WEB-INF/jsp/admin_cabinet.jsp";
    public static final String CLIENT_CABINET_URI = "WEB-INF/jsp/client_cabinet.jsp";
    public static final String DOCTOR_CABINET_URI = "WEB-INF/jsp/doctor_cabinet.jsp";
    public static final String PROBLEM_URI = "WEB-INF/jsp/problem_accured.jsp";
    public static final String REGISTRATION_URI = "WEB-INF/jsp/registration.jsp";
    public static final String SIGN_IN_URI = "WEB-INF/jsp/sign_in.jsp";
    public static final String DEFAULT_LANGUAGE = "en";

    private ControllerConstant() {
    }
}
