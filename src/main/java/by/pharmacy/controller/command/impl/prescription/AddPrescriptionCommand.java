package by.pharmacy.controller.command.impl.prescription;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddPrescriptionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        DoctorService doctorService = ServiceFactory.getInstance().getDoctorService();

        HttpSession session = request.getSession();
        String doctorLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        String clientLogin = request.getParameter(ControllerConstant.LOGIN_ATTRIBUTE);
        int dayCount = Integer.parseInt(request.getParameter(ControllerConstant.DAY_COUNT_ATTRIBUTE));

        int drugId = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_ID_ATTRIBUTE));
        int drugNumber = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_NUMBER_ATTRIBUTE));

        Drug drug = new Drug();
        drug.setId(drugId);
        drug.setNumber(drugNumber);

        doctorService.addPrescription(doctorLogin,clientLogin,drug,dayCount);
    }
}
