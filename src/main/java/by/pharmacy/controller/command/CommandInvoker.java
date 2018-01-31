package by.pharmacy.controller.command;

import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final static CommandInvoker instance = new CommandInvoker();
    private final Map<String,Command> commands = new HashMap<>();

    public void addCommand(String name, Command command){
        this.commands.put(name,command);
    }

    public void invokeCommand(String commandName, HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        Command command = commands.get(commandName);
        command.execute(request,response);
    }


    public static CommandInvoker getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "CommandInvoker{" +
                "commands=" + commands +
                '}';
    }
}
