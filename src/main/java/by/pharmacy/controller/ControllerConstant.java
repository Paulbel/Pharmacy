package by.pharmacy.controller;

public final class ControllerConstant {
    public static final String LOGIN_ATTRIBUTE = "login";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String SURNAME_ATTRIBUTE = "surname";
    public static final String PATRONYMIC_ATTRIBUTE = "patronymic";
    public static final String PHONE_ATTRIBUTE = "phone";
    public static final String EMAIL_ATTRIBUTE = "email";
    public static final String LOCAL_ATTRIBUTE = "local";
    public static final String USER_ID_ATTRIBUTE = "user_id";
    public static final String USERS_ATTRIBUTE = "users";
    public static final String COMMAND_ATTRIBUTE = "command";
    public static final String DRUGS_ATTRIBUTE = "drugs";

    public static final String ROLE_ATTRIBUTE = "role";

    public static final String MAIN_PAGE_URI = "index.jsp";
    public static final String ENTER_CABINET_COMMAND = "enter_cabinet";
    public static final String SHOW_USERS_COMMAND = "show_users";
    public static final String REGISTRATION_COMMAND = "registration";
    public static final String SHOW_ALL_DRUGS_COMMAND = "show_all_drugs";
    public static final String SIGN_IN_COMMAND = "sign_in";
    public static final String SIGN_OUT_COMMAND = "sign_out";

    public static final String CHANGE_LANGUAGE_COMMAND = "change_language";
    public static final String GIVE_ROLE_COMMAND = "give_role";
    public static final String EXCEPTION_MESSAGE = "exception_message";

    public static final String PHARMACIST_CABINET_URI = "WEB-INF/jsp/pharmacist_cabinet.jsp";
    public static final String ADMIN_CABINET_URI = "WEB-INF/jsp/admin_cabinet.jsp";
    public static final String PROBLEM_URI = "WEB-INF/jsp/problem_accured.jsp";

    private ControllerConstant() {
    }
}
