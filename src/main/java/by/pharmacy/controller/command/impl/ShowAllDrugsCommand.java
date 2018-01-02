package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllDrugsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        int page = 1;
        int recordsPerPage = 30;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        factory = ServiceFactory.getInstance();
        PharmacistService pharmacistService = factory.getPharmacistService();
        pharmacistService.getDrugNumber();
        List<Drug> list = pharmacistService.getDrugs(Language.RUSSIAN, recordsPerPage,
                (page - 1) * recordsPerPage);
        for(Drug drug:list){
            System.out.println(drug.getManufacturer().getName());
        }
        int noOfRecords = pharmacistService.getDrugNumber();
        int noOfPages = (int) Math.ceil(noOfRecords / recordsPerPage);
        request.setAttribute(ControllerConstant.DRUGS_ATTRIBUTE, list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher(ControllerConstant.PHARMACIST_CABINET_URI);
        view.forward(request, response);

    }
}
