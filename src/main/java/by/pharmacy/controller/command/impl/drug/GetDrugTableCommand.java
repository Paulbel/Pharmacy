package by.pharmacy.controller.command.impl.drug;

import by.pharmacy.controller.command.Command;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDrugTableCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        request.getRequestDispatcher("WEB-INF/jsp/drug_table.jsp").forward(request, response);
    }
}
