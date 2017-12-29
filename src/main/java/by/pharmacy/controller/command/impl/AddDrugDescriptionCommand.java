package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.dao.DAOFactory;
import by.pharmacy.dao.DrugDAO;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddDrugDescriptionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        int drugId = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_ID_ATTRIBUTE));
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        String name = request.getParameter(ControllerConstant.DRUG_NAME_ATTRIBUTE);
        String description = request.getParameter(ControllerConstant.DRUG_DESCRIPTION_ATTRIBUTE);
        String composition = request.getParameter(ControllerConstant.DRUG_COMPOSITION_ATTRIBUTE);


        Drug drug = new Drug();
        drug.setId(drugId);
        drug.setName(name);
        drug.setComposition(composition);
        drug.setDescription(description);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PharmacistService service = serviceFactory.getPharmacistService();
        service.addDrugDescription(login, drug, Language.RUSSIAN);
    }
}
