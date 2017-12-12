package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.User;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllUsersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        AdministratorService service = factory.getAdministratorService();
        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute(ControllerConstant.USER_ID_ATTRIBUTE);
        List<User> users = service.showUsers(id);
        request.setAttribute(ControllerConstant.USERS_ATTRIBUTE, users);

        RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.ADMIN_CABINET_URI);
        dispatcher.forward(request, response);

    }
}
