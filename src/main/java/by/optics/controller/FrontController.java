package by.optics.controller;

import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;
import by.optics.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
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
            dispatcher.forward(request, response);
        }
    }
}
