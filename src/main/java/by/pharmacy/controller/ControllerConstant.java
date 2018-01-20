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
    public static final String COMMAND_ATTRIBUTE = "command";
    public static final String PREV_COMMAND_ATTRIBUTE = "prev_command";

    public static final String MANUFACTURER_ATTRIBUTE = "manufacturer";
    public static final String MANUFACTURER_ID_ATTRIBUTE = "manufacturer_id";

    public static final String DRUG_ATTRIBUTE = "drug";
    public static final String DRUGS_ATTRIBUTE = "drugs";
    public static final String DRUG_ID_ATTRIBUTE = "drug_id";
    public static final String DRUG_NAME_ATTRIBUTE = "drug_name";
    public static final String DRUG_DESCRIPTION_ATTRIBUTE = "drug_description";
    public static final String DRUG_COMPOSITION_ATTRIBUTE = "drug_composition";
    public static final String LANGUAGES_ATTRIBUTE = "languages";

    public static final String ROLE_ATTRIBUTE = "role";

    public static final String MAIN_PAGE_URI = "index.jsp";
    public static final String ENTER_CABINET_COMMAND = "enter_cabinet";
    public static final String FIND_DRUG_COMMAND = "find_drug";
    public static final String SHOW_USERS_COMMAND = "show_users";
    public static final String SIGN_UP_COMMAND = "sign_up";
    public static final String GET_DRUGS_COMMAND = "show_all_drugs";
    public static final String GET_DRUG_COMMAND = "get_drug";
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_OUT_COMMAND = "sign_out";
    public static final String ADD_DESCRIPTION_COMMAND = "add_drug_description";
    public static final String GET_MANUFACTURER_COMMAND = "get_manufacturer";
    public static final String CHANGE_DRUG_DESCRIPTION_COMMAND = "change_drug_description";
    public static final String CHANGE_DRUG_INFO_COMMAND = "change_drug_info";


    public static final String CHANGE_LANGUAGE_COMMAND = "change_language";
    public static final String GET_LANGUAGE_AND_DRUGS_COMMAND = "get_language_drugs";
    public static final String GIVE_ROLE_COMMAND = "give_role";
    public static final String EXCEPTION_MESSAGE = "exception_message";

    public static final String PHARMACIST_CABINET_URI = "WEB-INF/jsp/pharmacist_cabinet.jsp";
    public static final String ADMIN_CABINET_URI = "WEB-INF/jsp/admin_cabinet.jsp";
    public static final String PROBLEM_URI = "WEB-INF/jsp/problem_accured.jsp";
    public static final String DEFAULT_LANGUAGE = "en";

    private ControllerConstant() {
    }
}
