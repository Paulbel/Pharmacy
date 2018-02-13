package by.pharmacy.controller.command.impl.user;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.User;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        AdministratorService administratorService = serviceFactory.getAdministratorService();
        HttpSession session = request.getSession();

        String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        String adminLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        User user = administratorService.getUser(adminLogin, login);

        request.setAttribute(ControllerConstant.USER_ATTRIBUTE, user);
    }
}
