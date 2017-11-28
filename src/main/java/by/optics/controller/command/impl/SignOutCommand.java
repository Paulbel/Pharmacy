package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(ControllerConstant.ROLE_ATTRIBUTE);
        session.removeAttribute(ControllerConstant.NAME_ATTRIBUTE);
        session.removeAttribute(ControllerConstant.PATRONYMIC_ATTRIBUTE);
        session.removeAttribute(ControllerConstant.USER_ID_ATTRIBUTE);
        response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
    }
}
