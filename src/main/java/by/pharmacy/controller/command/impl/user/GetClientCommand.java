package by.pharmacy.controller.command.impl.user;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.User;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        DoctorService doctorService = serviceFactory.getDoctorService();
        HttpSession session = request.getSession();

        String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        String doctorLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        User user = doctorService.getClient(doctorLogin, login);

        request.setAttribute(ControllerConstant.USER_ATTRIBUTE, user);
    }
}
