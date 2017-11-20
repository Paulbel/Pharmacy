package by.optics.controller;

import by.optics.controller.command.Command;
import by.optics.controller.command.CommandDirector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandDirector.getCommand(commandName);
        command.execute(request,response);
        System.out.println(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandDirector.getCommand(commandName);
        command.execute(request,response);
        System.out.println(request);
    }
}
