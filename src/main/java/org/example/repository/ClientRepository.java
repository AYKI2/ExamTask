package org.example.repository;

import org.example.entity.Bank;
import org.example.entity.Client;

import java.util.List;

public interface ClientRepository {
    void saveClient(Client client);
    void deleteById(Long clientId);
    List<Client> getAllClients();
    Client findById(Long clientId);
}
