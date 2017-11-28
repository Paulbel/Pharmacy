package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;
import by.optics.entity.user.Role;
import by.optics.service.AdministratorService;
import by.optics.service.ServiceFactory;
import by.optics.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GiveRoleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        int adminId = (Integer) session.getAttribute(ControllerConstant.USER_ID_ATTRIBUTE);
        int userId = Integer.valueOf(request.getParameter(ControllerConstant.USER_ID_ATTRIBUTE));
        Role role = Role.valueOf(request.getParameter(ControllerConstant.ROLE_ATTRIBUTE));
        ServiceFactory factory = ServiceFactory.getInstance();
        AdministratorService service = factory.getAdministratorService();
        service.setUserRole(adminId, userId, role);
        Command command = CommandDirector.getCommand(ControllerConstant.SHOW_USERS_COMMAND);
        command.execute(request, response);

    }
}
