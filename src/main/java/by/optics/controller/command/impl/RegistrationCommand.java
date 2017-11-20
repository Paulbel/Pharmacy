package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstants;
import by.optics.controller.command.Command;
import by.optics.entity.User;
import by.optics.service.ServiceFactory;
import by.optics.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(ControllerConstants.NAME);
        String surname = request.getParameter(ControllerConstants.SURNAME);
        String patronymic = request.getParameter(ControllerConstants.PATRONYMIC);
        String login = request.getParameter(ControllerConstants.LOGIN);
        String password = request.getParameter(ControllerConstants.PASSWORD);
        String phone = request.getParameter(ControllerConstants.PHONE);
        String email = request.getParameter(ControllerConstants.EMAIL);

        User user = new User();

        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        user.setLogin(login);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        service.registration(user);
        try {
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
