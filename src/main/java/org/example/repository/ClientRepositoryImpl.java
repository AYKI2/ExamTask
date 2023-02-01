package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.Client;
import org.example.entity.Region;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getSession();
    public void saveClient(Client client) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(Long clientId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, clientId);
            entityManager.remove(client);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            clients.addAll(entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList());
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public Client findById(Long clientId) {
        Client client = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            client = entityManager.find(Client.class, clientId);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return client;
    }

    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
