package by.pharmacy.controller.command.impl.cabinet;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Role;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnterCabinetCommand implements Command {
    private final Map<Role, String> pageMap = new HashMap<Role, String>() {{
        put(Role.ADMIN, ControllerConstant.ADMIN_CABINET_URI);
        put(Role.PHARMACIST, ControllerConstant.PHARMACIST_CABINET_URI);
        put(Role.DOCTOR, ControllerConstant.DOCTOR_CABINET_URI);
        put(Role.CLIENT, ControllerConstant.CLIENT_CABINET_URI);
    }};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();

        String roleName = (String) session.getAttribute(ControllerConstant.ROLE_ATTRIBUTE);
        Role role = Role.CLIENT;
        if (roleName != null) {
            role = Role.valueOf(roleName);
        }
        String address = pageMap.get(role);

        String commandName = request.getParameter(ControllerConstant.COMMAND_ATTRIBUTE);
        String currentOption = request.getParameter(ControllerConstant.CURRENT_OPTION_ATTRIBUTE);
        if (currentOption != null) {
            commandName = currentOption;
        }
        request.setAttribute(ControllerConstant.PREV_COMMAND_ATTRIBUTE, commandName);

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}