package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.Region;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfig.getSession();
    public void saveRegion(Region region) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(region);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            regions.addAll(entityManager.createQuery("SELECT r FROM Region r", Region.class).getResultList());
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return regions;
    }

    public void update(Region newRegion, Long oldRegionId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Region region = entityManager.find(Region.class, oldRegionId);
            region.setRegionName(newRegion.getRegionName());
            region.setRegionName(region.getRegionName());
            entityManager.merge(region);
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
