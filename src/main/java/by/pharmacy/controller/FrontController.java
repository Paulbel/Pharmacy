package by.pharmacy.controller;

import by.pharmacy.controller.command.Command;
import by.pharmacy.controller.command.CommandDirector;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = -2081650491757405193L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(ControllerConstant.COMMAND_ATTRIBUTE);
        Command command = CommandDirector.getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (ServiceException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.PROBLEM_URI);
            request.setAttribute(ControllerConstant.EXCEPTION_MESSAGE, e.getMessage());
            dispatcher.forward(request, response);
        }
    }
}
