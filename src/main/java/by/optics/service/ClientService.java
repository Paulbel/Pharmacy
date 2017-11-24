package by.optics.service;

import by.optics.entity.user.Client;

public abstract class ClientService{
     abstract void buy(int id);
     abstract void registration(Client client);
}
