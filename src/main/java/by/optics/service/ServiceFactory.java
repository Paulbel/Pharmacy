package by.optics.service;

import by.optics.service.impl.AdministratorServiceImpl;
import by.optics.service.impl.UserServiceImpl;


public final class ServiceFactory {
    public static final ServiceFactory instance = new ServiceFactory();
    private UserService userService = new UserServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }
}
