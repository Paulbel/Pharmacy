package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.command.Command;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetManufacturerCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {

    }
}
