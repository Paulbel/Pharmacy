package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.controller.command.CommandDirector;
import by.pharmacy.entity.User;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
            String password = request.getParameter(ControllerConstant.PASSWORD_ATTRIBUTE);

            User user = userService.signIn(login, password);
            HttpSession session = request.getSession(true);

            session.setAttribute(ControllerConstant.USER_ID_ATTRIBUTE, user.getId());
            session.setAttribute(ControllerConstant.NAME_ATTRIBUTE, user.getName());
            session.setAttribute(ControllerConstant.SURNAME_ATTRIBUTE, user.getSurname());
            session.setAttribute(ControllerConstant.ROLE_ATTRIBUTE, String.valueOf(user.getRole()));
            Command command = CommandDirector.getCommand(ControllerConstant.ENTER_CABINET_COMMAND);
            command.execute(request, response);




    }
}
