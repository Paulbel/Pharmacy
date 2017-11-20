package by.optics.controller.command.impl;

import by.optics.controller.command.Command;
import by.optics.entity.Client;
import by.optics.entity.Doctor;
import by.optics.entity.User;
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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            User user = userService.signIn(login,password);
            HttpSession session = request.getSession(true);
            session.setAttribute("user",user);
            if(user instanceof Client){
                session.setAttribute("role","client");
            }else if(user instanceof Doctor){
                session.setAttribute("role","doctor");
            } else if(user instanceof User){
                session.setAttribute("role","user");
            }
            response.sendRedirect("index.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
