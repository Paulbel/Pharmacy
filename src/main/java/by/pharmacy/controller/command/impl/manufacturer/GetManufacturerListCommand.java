package by.pharmacy.controller.command.impl.manufacturer;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetManufacturerListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        UserService userService = serviceFactory.getUserService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);

        Language language = Language.valueOf(languageName.toUpperCase());
        int manufacturerNumber = userService.getManufacturerCount(language);
        List<Manufacturer> manufacturerList = userService.getManufacturers(language, manufacturerNumber, 0);
        request.setAttribute(ControllerConstant.MANUFACTURER_LIST_ATTRIBUTE, manufacturerList);
    }
}
