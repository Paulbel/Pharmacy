package by.pharmacy.controller.command.impl.user;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Role;
import by.pharmacy.service.AdministratorService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GiveRoleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();

        String administratorLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        String userLogin = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        Role role = Role.valueOf(request.getParameter(ControllerConstant.ROLE_ATTRIBUTE));

        ServiceFactory factory = ServiceFactory.getInstance();
        AdministratorService service = factory.getAdministratorService();

        service.setUserRole(administratorLogin, userLogin, role);
    }
}
