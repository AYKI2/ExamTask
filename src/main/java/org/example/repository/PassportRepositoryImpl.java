package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.Client;
import org.example.entity.Passport;
import org.example.entity.Region;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class PassportRepositoryImpl implements PassportRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getSession();
    public void savePassport(Passport passport) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(passport);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllPassports(Long passportId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Passport passport = entityManager.find(Passport.class, passportId);
            passport.getClient().setPassport(null);
            entityManager.remove(passport);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Passport> getAllPassports() {
        List<Passport> passports = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            passports.addAll(entityManager.createQuery("SELECT p FROM Passport p", Passport.class).getResultList());
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return passports;
    }

    public void assignPassportToClient(Long clientId, Long passportId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, clientId);
            Passport passport = entityManager.find(Passport.class, passportId);
            client.setPassport(passport);
            passport.setClient(client);
            entityManager.persist(client);
            entityManager.persist(passport);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
