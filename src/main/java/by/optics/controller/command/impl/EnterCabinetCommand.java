package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;
import by.optics.entity.user.Role;
import by.optics.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnterCabinetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        HttpSession session = request.getSession();
        Role role  = Role.valueOf( (String) session.getAttribute(ControllerConstant.ROLE_ATTRIBUTE));
        switch (role){
            case USER:
                response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
                break;
            case ADMIN:
                Command command = CommandDirector.getCommand(ControllerConstant.SHOW_USERS_COMMAND);
                command.execute(request,response);
                break;
            case DOCTOR:
                response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
                break;
        }
    }
}
