package by.optics.controller.command;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.impl.*;

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
        map.put(ControllerConstant.BAN_USER_COMMAND, new BanUserCommand());
        map.put(ControllerConstant.UNBAN_USER_COMMAND, new UnbanUserCommand());
        map.put(ControllerConstant.GIVE_ROLE_COMMAND, new GiveRoleCommand());
    }

    public static Command getCommand(String commandName) {
        return map.get(commandName);
    }

    private CommandDirector() {

    }

}
