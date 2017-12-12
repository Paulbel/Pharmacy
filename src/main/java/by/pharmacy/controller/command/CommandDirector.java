package by.pharmacy.controller.command;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandDirector {
    private static Map<String, Command> map = new HashMap<>();

    static {
        map.put(ControllerConstant.REGISTRATION_COMMAND, new RegistrationCommand());
        map.put(ControllerConstant.SIGN_IN_COMMAND, new SignInCommand());
        map.put(ControllerConstant.SIGN_OUT_COMMAND, new SignOutCommand());
        map.put(ControllerConstant.CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());
        map.put(ControllerConstant.SHOW_USERS_COMMAND, new ShowAllUsersCommand());
        map.put(ControllerConstant.ENTER_CABINET_COMMAND, new EnterCabinetCommand());
        map.put(ControllerConstant.GIVE_ROLE_COMMAND, new GiveRoleCommand());
        map.put(ControllerConstant.SHOW_ALL_DRUGS_COMMAND, new ShowAllDrugsCommand());
    }

    public static Command getCommand(String commandName) {
        return map.get(commandName);
    }

    private CommandDirector() {
    }

}
