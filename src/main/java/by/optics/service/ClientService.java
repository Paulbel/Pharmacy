package by.optics.service;

import by.optics.entity.user.Client;

public interface ClientService {
    void buy(int id);

    void registration(Client client);
}
