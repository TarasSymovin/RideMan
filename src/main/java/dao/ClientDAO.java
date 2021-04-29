package dao;

import entity.Client;

public interface ClientDAO {
    void add(Client client);
    Client getById(String number);
    void update(Client client);
}
