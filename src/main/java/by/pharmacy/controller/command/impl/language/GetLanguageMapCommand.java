package by.pharmacy.controller.command.impl.language;

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
import java.util.Map;

public class GetLanguageMapCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        HttpSession session = request.getSession();

        Language currentLocale = Language.valueOf(session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE).toString().toUpperCase());

        Map<String,Language> languageMap = userService.getLanguages(currentLocale);
        request.setAttribute(ControllerConstant.LANGUAGES_ATTRIBUTE,languageMap);
    }


}
