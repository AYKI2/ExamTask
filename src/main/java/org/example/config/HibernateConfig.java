package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Bank;
import org.example.entity.Client;
import org.example.entity.Passport;
import org.example.entity.Region;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory getSession(){
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/java8");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "admin123");

            properties.put(Environment.HBM2DDL_AUTO, "create");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Bank.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Passport.class);
            configuration.addAnnotatedClass(Region.class);
            System.out.println("Successfully connected!");
            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
