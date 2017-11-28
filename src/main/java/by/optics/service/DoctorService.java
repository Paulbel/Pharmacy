package by.optics.service;


import by.optics.entity.user.User;

public interface DoctorService {
    void inspect(User client, User doctor);
}
