package by.pharmacy.controller.command.impl.order;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Order;
import by.pharmacy.service.ClientService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetOrderListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {

        int page = 1;
        int recordsPerPage = 30;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ServiceFactory factory = ServiceFactory.getInstance();
        ClientService clientService = factory.getClientService();

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);
        String languageName = (String) session.getAttribute(ControllerConstant.LOCAL_ATTRIBUTE);


        Language language = Language.valueOf(languageName.toUpperCase());
        int noOfRecords = clientService.getOrderCount(login, language);

        List<Order> list = clientService.getOrderList(login, recordsPerPage,
                (page - 1) * recordsPerPage, language);

        int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);
        request.setAttribute(ControllerConstant.ORDER_LIST_ATTRIBUTE, list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);


    }
}
