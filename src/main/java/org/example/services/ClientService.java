package org.example.services;

import org.example.entity.Client;

import java.util.List;

public interface ClientService {
    String saveClient(Client client);
    String deleteById(Long clientId);
    List<Client> getAllClients();
    Client findById(Long clientId);
}
