package by.pharmacy.controller.command.impl.prescription;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Prescription;
import by.pharmacy.service.ClientService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetPrescriptionListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ClientService clientService = ServiceFactory.getInstance().getClientService();

        HttpSession session = request.getSession();
        String doctorLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);
        List<Prescription> prescriptionList = clientService.getPrescriptionList(doctorLogin, Language.valueOf(languageName.toUpperCase()));

        request.setAttribute(ControllerConstant.PRESCRIPTION_LIST_ATTRIBUTE, prescriptionList);
    }
}
