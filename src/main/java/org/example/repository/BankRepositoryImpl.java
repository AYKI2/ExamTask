package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.HibernateConfig;
import org.example.entity.Bank;
import org.example.entity.Client;
import org.example.entity.Passport;
import org.example.entity.Region;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getSession();
    public void saveBank(Bank bank, Long regionId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Region region = entityManager.find(Region.class, regionId);
            bank.setRegion(region);
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteBank(Long bankId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM Bank b WHERE b.id = :bankId ")
                    .setParameter("bankId",bankId).executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> bankList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            bankList.addAll(entityManager.createQuery("SELECT b FROM Bank b", Bank.class).getResultList());
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return bankList;
    }

    public List<Bank> getBanksByRegionName(String name) {
        List<Bank> bankList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Bank> resultList = entityManager.createQuery("SELECT b FROM Bank b " +
                            "JOIN Region r on b.region.id = r.id WHERE r.regionName = :name")
                    .setParameter("name",name)
                    .getResultList();
            bankList.addAll(resultList);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return bankList;
    }

    @Override
    public void assignBankToClient(Long clientId, Long bankId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Client client = entityManager.find(Client.class, clientId);
            Bank bank = entityManager.find(Bank.class, bankId);
            client.getBankList().add(bank);
            bank.getClientList().add(client);
            entityManager.persist(client);
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
