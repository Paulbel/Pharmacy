package by.optics.controller.command;

import by.optics.controller.command.impl.RegistrationCommand;
import by.optics.controller.command.impl.SignInCommand;
import by.optics.controller.command.impl.SignOutCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandDirector {
    private static Map<String,Command> map = new HashMap<>();

    static {
        map.put("registration", new RegistrationCommand());
        map.put("signIn", new SignInCommand());
        map.put("signOut", new SignOutCommand());
    }

    public static Command getCommand(String commandName){
        return map.get(commandName);
    }

    private CommandDirector(){

    }

}
