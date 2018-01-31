package by.pharmacy.controller.command.impl.drug;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
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

public class GetDrugListCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        int page = 1;
        int recordsPerPage = 30;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);


        Language language = Language.valueOf(languageName.toUpperCase());

        List<Drug> list = userService.getDrugs(language, recordsPerPage,
                (page - 1) * recordsPerPage);
        int noOfRecords = userService.getDrugCount(language);
        int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);
        request.setAttribute(ControllerConstant.DRUGS_ATTRIBUTE, list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
    }

}
