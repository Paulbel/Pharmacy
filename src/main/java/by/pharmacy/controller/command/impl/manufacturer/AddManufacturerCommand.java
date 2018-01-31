package by.pharmacy.controller.command.impl.manufacturer;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Country;
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

public class AddManufacturerCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        PharmacistService pharmacistService = factory.getPharmacistService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);

        Language language = Language.valueOf(languageName.toUpperCase());

        String name = request.getParameter(ControllerConstant.MANUFACTURER_NAME_ATTRIBUTE);
        String phoneNumber = request.getParameter(ControllerConstant.PHONE_ATTRIBUTE);
        String address = request.getParameter(ControllerConstant.MANUFACTURER_ADDRESS_ATTRIBUTE);
        String email = request.getParameter(ControllerConstant.EMAIL_ATTRIBUTE);
        String country_code = request.getParameter(ControllerConstant.COUNTRY_CODE_ATTRIBUTE);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setPhoneNumber(phoneNumber);
        manufacturer.setEmail(email);
        manufacturer.setAddress(address);

        Country country = new Country();
        country.setCode(country_code);
        manufacturer.setCountry(country);

        pharmacistService.addManufacturer(manufacturer, language);
    }
}
