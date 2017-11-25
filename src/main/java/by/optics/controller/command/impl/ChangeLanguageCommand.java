package by.optics.controller.command.impl;

import by.optics.controller.ControllerConstant;
import by.optics.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter(ControllerConstant.LOCAL_ATTRIBUTE);
        session.setAttribute(ControllerConstant.LOCAL_ATTRIBUTE, language);
        try {
            response.sendRedirect(ControllerConstant.MAIN_PAGE_URI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
