package by.pharmacy.service;

import by.pharmacy.service.impl.*;


public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();
    private PharmacistService pharmacistService = new PharmacistServiceImpl();
    private DoctorService doctorService = new DoctorServiceImpl();
    private ClientService clientService = new ClientServiceImpl();


    public ClientService getClientService() {
        return clientService;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public PharmacistService getPharmacistService() {
        return pharmacistService;
    }

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
