package org.example.services;

import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.example.repository.ClientRepositoryImpl;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository = new ClientRepositoryImpl();
    public String saveClient(Client client) {
        clientRepository.saveClient(client);
        return "Successfully saved!";
    }

    public String deleteById(Long clientId) {
        clientRepository.deleteById(clientId);
        return "Successfully deleted!";
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client findById(Long clientId) {
        return clientRepository.findById(clientId);
    }
}
