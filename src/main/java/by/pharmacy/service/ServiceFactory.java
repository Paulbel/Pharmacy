package by.pharmacy.service;

import by.pharmacy.service.impl.AdministratorServiceImpl;
import by.pharmacy.service.impl.DoctorServiceImpl;
import by.pharmacy.service.impl.PharmacistServiceImpl;
import by.pharmacy.service.impl.UserServiceImpl;


public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private UserService userService = new UserServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();
    private PharmacistService pharmacistService = new PharmacistServiceImpl();
    private DoctorService doctorService = new DoctorServiceImpl();

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
