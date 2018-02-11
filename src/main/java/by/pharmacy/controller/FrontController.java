package by.pharmacy.controller;

import by.pharmacy.controller.command.CommandInvoker;
import by.pharmacy.controller.command.CommandSaxHandler;
import by.pharmacy.service.exception.ServiceException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = -2081650491757405193L;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            CommandInvoker commandInvoker = CommandInvoker.getInstance();
            XMLReader reader = XMLReaderFactory.createXMLReader();

            CommandSaxHandler commandSaxHandler = new CommandSaxHandler();
            reader.setContentHandler(commandSaxHandler);
            reader.parse(new InputSource(new FileInputStream(getFilePath())));

            commandInvoker.setCommands(commandSaxHandler.getCommandMap());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(ControllerConstant.COMMAND_ATTRIBUTE);
        try {
            CommandInvoker commandInvoker = CommandInvoker.getInstance();
            commandInvoker.invokeCommand(commandName, request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath() {
        URL fileURL = this.getClass().getClassLoader().getResource("xml/command.xml");
        String filePath = null;

        if (fileURL != null) {
            filePath = fileURL.getPath();
        }

        return filePath;
    }
}
