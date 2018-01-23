package by.pharmacy.controller.command.impl;

import by.pharmacy.controller.ControllerConstant;
import by.pharmacy.controller.command.Command;
import by.pharmacy.entity.User;
import by.pharmacy.service.DoctorService;
import by.pharmacy.service.ServiceFactory;
import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetClientListCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException {

        ServiceFactory factory = ServiceFactory.getInstance();
        DoctorService doctorService = factory.getDoctorService();

        List<User> list = doctorService.getClientList();




        int page = 1;
        int recordsPerPage = 30;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int noOfRecords = list.size();
        int noOfPages = (int) Math.ceil(noOfRecords / recordsPerPage);


        int fromIndex = recordsPerPage*(page-1);
        int toIndex = recordsPerPage+fromIndex;
        if(fromIndex+recordsPerPage>noOfRecords){
            toIndex = noOfRecords;
        }
        List<User> subList = list.subList(fromIndex,toIndex);



        
        request.setAttribute(ControllerConstant.USERS_ATTRIBUTE, subList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
    }
}
