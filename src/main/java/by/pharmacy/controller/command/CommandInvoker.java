package by.pharmacy.controller.command;

import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CommandInvoker {
    private final static CommandInvoker instance = new CommandInvoker();
    private Map<String, Command> commands = new HashMap<>();

    private CommandInvoker() {
    }

    public void invokeCommand(String commandName, HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        Command command = commands.get(commandName);
        command.execute(request,response);
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }


    public static CommandInvoker getInstance() {
        return instance;
    }
}
