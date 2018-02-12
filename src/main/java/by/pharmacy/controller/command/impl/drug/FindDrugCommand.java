package by.pharmacy.controller.command.impl.drug;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.DrugCriteria;
import by.pharmacy.entity.Language;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FindDrugCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);
        Language language = Language.valueOf(languageName.toUpperCase());

        String name = request.getParameter(ControllerConstant.DRUG_NAME_ATTRIBUTE);

        List<Drug> list = userService.findDrug(name, language, DrugCriteria.NAME);

        request.setAttribute(ControllerConstant.DRUG_NAME_ATTRIBUTE, name);
        request.setAttribute(ControllerConstant.DRUGS_ATTRIBUTE, list);
    }
}
