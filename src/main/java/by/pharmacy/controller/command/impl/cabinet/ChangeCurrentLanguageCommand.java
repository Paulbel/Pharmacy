package by.pharmacy.controller.command.impl.cabinet;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeCurrentLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = request.getParameter(ControllerConstant.LOCAL_ATTRIBUTE);

        session.setAttribute(ControllerConstant.LOCAL_ATTRIBUTE, language);
        String address = formRedirectAddress(request);
        response.sendRedirect(address);
    }

    private String formRedirectAddress(HttpServletRequest request) {
        String query = request.getParameter("query");
        StringBuilder addressConstructor = new StringBuilder();
        addressConstructor.append("/FrontController");
        if (query != "") {
            addressConstructor.append("?command=");
            addressConstructor.append(query);
        }
        return addressConstructor.toString();
    }
}



