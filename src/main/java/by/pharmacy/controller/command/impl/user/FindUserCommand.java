package by.pharmacy.controller.command.impl.user;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.User;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        DoctorService doctorService = serviceFactory.getDoctorService();

        String name = request.getParameter(ControllerConstant.NAME_ATTRIBUTE);

        List<User> list = doctorService.findUser(name);

        request.setAttribute(ControllerConstant.MANUFACTURER_NAME_ATTRIBUTE, name);
        request.setAttribute(ControllerConstant.MANUFACTURER_LIST_ATTRIBUTE, list);
    }
}
