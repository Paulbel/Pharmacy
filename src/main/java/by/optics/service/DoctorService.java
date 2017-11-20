package by.optics.service;

import by.optics.entity.Client;
import by.optics.entity.Doctor;

public interface DoctorService{
    void inspect(Client client, Doctor doctor);
}
