package by.pharmacy.controller.command;

import by.pharmacy.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException, ServletException;
    protected List<Command> commandList = new ArrayList<>();


    public String formRedirectAddress(HttpServletRequest request){
        String query = request.getParameter("query");
        StringBuilder addressConstructor = new StringBuilder();
        addressConstructor.append("/FrontController");
        if(query!=null){
            addressConstructor.append("?command=");
            addressConstructor.append(query);
        }
        return addressConstructor.toString();
    }

    public void addCommand(Command command){
        this.commandList.add(command);
    }

    public void removeCommand(Command command){
        this.commandList.remove(command);
    }

    public void getChildCommand(int index){
        this.commandList.remove(index);
    }
}
