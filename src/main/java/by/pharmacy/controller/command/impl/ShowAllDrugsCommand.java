package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllDrugsCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        PharmacistService service = factory.getPharmacistService();

        List<Drug> drugList = service.getAllDrugs(Language.RUSSIAN);//TODO: localization

        request.setAttribute(ControllerConstant.DRUGS_ATTRIBUTE,drugList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ControllerConstant.PHARMACIST_CABINET_URI);
        dispatcher.forward(request, response);
    }
}
