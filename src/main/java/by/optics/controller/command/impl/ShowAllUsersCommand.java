package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.entity.user.User;
import by.optics.service.AdministratorService;
import by.optics.service.ServiceFactory;
import by.optics.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllUsersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        AdministratorService service = factory.getAdministratorService();
        List<User> users = service.showUsers();
        request.setAttribute(ControllerConstant.USERS_ATTRIBUTE, users);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ADMIN_CABINET_URI);
        dispatcher.forward(request, response);
    }
}
