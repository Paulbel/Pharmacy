package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;
import by.optics.dao.exception.DAOException;
import by.optics.service.AdministratorService;
import by.optics.service.ServiceFactory;
import by.optics.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanUserCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        AdministratorService service = factory.getAdministratorService();
        try {
            service.banUser(Integer.valueOf(request.getParameter(ControllerConstant.USER_TO_BAN)));
            Command command = CommandDirector.getCommand(ControllerConstant.SHOW_USERS_COMMAND);
            command.execute(request,response);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
