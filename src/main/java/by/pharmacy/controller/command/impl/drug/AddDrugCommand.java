package by.pharmacy.controller.command.impl.drug;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.service.PharmacistService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddDrugCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        PharmacistService pharmacistService = factory.getPharmacistService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);

        Language language = Language.valueOf(languageName.toUpperCase());

        String name = request.getParameter(ControllerConstant.DRUG_NAME_ATTRIBUTE);
        String composition = request.getParameter(ControllerConstant.DRUG_COMPOSITION_ATTRIBUTE);
        String description = request.getParameter(ControllerConstant.DRUG_DESCRIPTION_ATTRIBUTE);
        int drugAmount = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_AMOUNT_ATTRIBUTE));
        int drugDosage = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_DOSAGE_ATTRIBUTE));
        int drugNumber = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_NUMBER_ATTRIBUTE));
        double drugPrice = Double.valueOf(request.getParameter(ControllerConstant.DRUG_PRICE_ATTRIBUTE));
        int manufacturerId = Integer.valueOf(request.getParameter(ControllerConstant.MANUFACTURER_ID_ATTRIBUTE));

        boolean needPrescription = false;
        if (request.getParameter(ControllerConstant.DRUG_NEED_PRESCRIPTION_ATTRIBUTE) != null) {
            needPrescription = true;
        }

        Drug drug = new Drug();
        drug.setName(name);
        drug.setComposition(composition);
        drug.setDescription(description);
        drug.setAmount(drugAmount);
        drug.setDosage(drugDosage);
        drug.setNeedPrescription(needPrescription);
        drug.setNumber(drugNumber);
        drug.setPrice(drugPrice);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        drug.setManufacturer(manufacturer);

        pharmacistService.addDrug(drug, language);
    }
}
