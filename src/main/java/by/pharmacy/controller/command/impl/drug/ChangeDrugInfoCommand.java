package by.pharmacy.controller.command.impl.drug;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeDrugInfoCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        PharmacistService pharmacistService = factory.getPharmacistService();

        int drugId = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_ID_ATTRIBUTE));
        int drugAmount = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_AMOUNT_ATTRIBUTE));
        int drugDosage = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_DOSAGE_ATTRIBUTE));
        int drugNumber = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_NUMBER_ATTRIBUTE));
        double drugPrice = Double.valueOf(request.getParameter(ControllerConstant.DRUG_PRICE_ATTRIBUTE));

        boolean needPrescription = false;

        if (request.getParameter(ControllerConstant.DRUG_NEED_PRESCRIPTION_ATTRIBUTE) != null) {
            needPrescription = true;
        }

        Drug drug = new Drug();
        drug.setId(drugId);
        drug.setAmount(drugAmount);
        drug.setDosage(drugDosage);
        drug.setNeedPrescription(needPrescription);
        drug.setNumber(drugNumber);
        drug.setPrice(drugPrice);

        pharmacistService.changeDrugInfo(drug);
    }
}
