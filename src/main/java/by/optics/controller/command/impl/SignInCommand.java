package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.entity.user.User;
import by.optics.service.ServiceFactory;
import by.optics.service.UserService;
import by.optics.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        String password = request.getParameter(ControllerConstant.PASSWORD_ATTRIBUTE);
        try {
            User user = userService.signIn(login, password);
            HttpSession session = request.getSession(true);
            session.setAttribute(ControllerConstant.USER_ATTRIBUTE, user);
            response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }
}
