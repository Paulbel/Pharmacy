package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.controller.command.CommandDirector;
import by.pharmacy.entity.Role;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnterCabinetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        Role role = Role.valueOf((String) session.getAttribute(ControllerConstant.ROLE_ATTRIBUTE));
        Command command = null;
        switch (role) {
            case USER:
                response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
                //TODO: User cabinet logic
                break;
            case ADMIN:
                command = CommandDirector.getCommand(ControllerConstant.SHOW_USERS_COMMAND);
                break;
            case DOCTOR:
                response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
                //TODO: Doctor cabinet logic
                break;
            case PHARMACIST:
                command = CommandDirector.getCommand(ControllerConstant.SHOW_ALL_DRUGS_COMMAND);
                break;
        }
        command.execute(request,response);
    }
}
