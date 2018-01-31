package by.pharmacy.controller.command;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandSaxHandler extends DefaultHandler {
    private StringBuilder text;
    private Command command;
    private String commandName;
    private CommandInvoker commandInvoker;
    private Map<String, Command> commandMap;


    private final String NAME = "name";
    private final String CLASS = "class";
    private final String COMMAND_NAME = "command_name";
    private final String COMMAND = "command";
    private final String MACRO_COMMAND = "macro_command";


    public CommandSaxHandler(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
        this.commandMap = new HashMap<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        switch (localName){
            case MACRO_COMMAND:
                command = new MacroCommand();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case COMMAND:
                commandMap.put(commandName, command);
                commandInvoker.addCommand(commandName, command);
                break;
            case CLASS:
                try {
                    Class classInstance = Class.forName(String.valueOf(text));
                    Object obj = classInstance.newInstance();
                    command = (Command) obj;
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            case MACRO_COMMAND:
                commandInvoker.addCommand(commandName, command);
                break;
            case COMMAND_NAME:
                command.addCommand(commandMap.get(String.valueOf(text)));
                break;
            case NAME:
                commandName = String.valueOf(text);
                break;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }


}

