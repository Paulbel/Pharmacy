package by.pharmacy.controller.command.impl.manufacturer;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Language;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.UserService;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetManufacturerCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        HttpSession session = request.getSession();
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);

        Language language = Language.valueOf(languageName.toUpperCase());
        int manufacturerId = Integer.valueOf(request.getParameter(ControllerConstant.MANUFACTURER_ID_ATTRIBUTE));
        request.setAttribute(ControllerConstant.MANUFACTURER_ATTRIBUTE, userService.getManufacturer(manufacturerId, language));
    }
}
