package by.pharmacy.controller.command.impl.order;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Order;
import by.pharmacy.entity.User;
import by.pharmacy.service.ClientService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            ClientService clientService = factory.getClientService();

            HttpSession session = request.getSession();
            String clientLogin = (String) session.getAttribute(ControllerConstant.LOGIN_ATTRIBUTE);

            int drugId = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_ID_ATTRIBUTE));
            int drugNumber = Integer.valueOf(request.getParameter(ControllerConstant.DRUG_NUMBER_ATTRIBUTE));
            Order order = new Order();
            User client = new User();
            order.setNumber(drugNumber);
            client.setLogin(clientLogin);
            order.setClient(client);
            Drug drug = new Drug();
            drug.setId(drugId);

            order.setDrug(drug);


            clientService.addOrder(order);
        } catch (ServiceException e) {
            request.setAttribute(ControllerConstant.PROBLEM_DESCRIPTION, ControllerConstant.WRONG_DATA);
        }
    }
}
