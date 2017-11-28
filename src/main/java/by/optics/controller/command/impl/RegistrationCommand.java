package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;
import by.optics.entity.user.User;
import by.optics.service.ServiceFactory;
import by.optics.service.UserService;
import by.optics.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String name = request.getParameter(ControllerConstant.NAME_ATTRIBUTE);
        String surname = request.getParameter(ControllerConstant.SURNAME_ATTRIBUTE);
        String patronymic = request.getParameter(ControllerConstant.PATRONYMIC_ATTRIBUTE);
        String login = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        String password = request.getParameter(ControllerConstant.PASSWORD_ATTRIBUTE);
        String phone = request.getParameter(ControllerConstant.PHONE_ATTRIBUTE);
        String email = request.getParameter(ControllerConstant.EMAIL_ATTRIBUTE);

        User user = new User();

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
        response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
    }
}
