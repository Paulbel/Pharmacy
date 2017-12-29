package by.pharmacy.controller.command.impl;

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
    private static final Map<Role,String> pageMap = new HashMap<>();
    static {
        pageMap.put(Role.ADMIN,ControllerConstant.ADMIN_CABINET_URI);
        pageMap.put(Role.PHARMACIST,ControllerConstant.PHARMACIST_CABINET_URI);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        Role role = Role.valueOf((String) session.getAttribute(ControllerConstant.ROLE_ATTRIBUTE));

        String address = pageMap.get(role);
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}