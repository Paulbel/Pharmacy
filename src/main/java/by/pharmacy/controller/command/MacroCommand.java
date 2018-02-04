package by.pharmacy.controller.command;

import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {

    private List<Command> commandList = new ArrayList<>();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        for (Command command : commandList) {
            command.execute(request, response);
        }
    }

    public void addCommand(Command command) {
        this.commandList.add(command);
    }

    public MacroCommand() {
        this.commandList = new ArrayList<>();
    }
}
