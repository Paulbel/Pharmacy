package by.pharmacy.service;

import by.pharmacy.service.impl.AdministratorServiceImpl;
import by.pharmacy.service.impl.PharmacistServiceImpl;
import by.pharmacy.service.impl.UserServiceImpl;


public final class ServiceFactory {
    public static final ServiceFactory instance = new ServiceFactory();
    private UserService userService = new UserServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();

    public PharmacistService getPharmacistService() {
        return pharmacistService;
    }

    private PharmacistService pharmacistService = new PharmacistServiceImpl();
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
