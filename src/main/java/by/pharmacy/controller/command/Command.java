package by.pharmacy.controller.command;

import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException;


}
