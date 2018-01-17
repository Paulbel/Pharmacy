package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute(ControllerConstant.ROLE_ATTRIBUTE);
        session.removeAttribute(ControllerConstant.NAME_ATTRIBUTE);
        session.removeAttribute(ControllerConstant.LOGIN_ATTRIBUTE);

        response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
    }
}
