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

public class GetClientListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        DoctorService doctorService = serviceFactory.getDoctorService();
        List<User> list = doctorService.getClientList();

        request.setAttribute(ControllerConstant.USERS_ATTRIBUTE, list);
    }
}
