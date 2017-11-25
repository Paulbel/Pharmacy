package by.optics.service;

import by.optics.entity.user.Client;
import by.optics.entity.user.Doctor;

public interface DoctorService {
    void inspect(Client client, Doctor doctor);
}
