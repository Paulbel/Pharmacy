package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;
import by.optics.entity.Role;
import by.optics.entity.user.User;
import by.optics.service.ServiceFactory;
import by.optics.service.UserService;
import by.optics.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        String password = request.getParameter(ControllerConstant.PASSWORD_ATTRIBUTE);
        User user = userService.signIn(login, password);
        HttpSession session = request.getSession(true);
        session.setAttribute(ControllerConstant.USER_ID_ATTRIBUTE, user.getId());
        session.setAttribute(ControllerConstant.NAME_ATTRIBUTE, user.getName());
        session.setAttribute(ControllerConstant.PATRONYMIC_ATTRIBUTE, user.getPatronymic());

        Role userRole = userService.getUsersRole(user.getId());
        switch (userRole) {
            case USER:
                response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
                break;
            case ADMIN:
                Command command = CommandDirector.getCommand(ControllerConstant.SHOW_USERS_COMMAND);
                command.execute(request,response);
                break;
        }
        response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
    }
}
