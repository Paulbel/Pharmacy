package by.optics.service;

import by.optics.service.impl.UserServiceImpl;


public final class ServiceFactory {
    public static final ServiceFactory instance = new ServiceFactory();
    private UserService userService = new UserServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }
}
