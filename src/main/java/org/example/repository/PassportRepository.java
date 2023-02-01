package org.example.repository;

import org.example.entity.Bank;
import org.example.entity.Passport;

import java.util.List;

public interface PassportRepository {
    void savePassport(Passport passport);
    void deleteAllPassports(Long passportId);
    List<Passport> getAllPassports();
    void assignPassportToClient(Long clientId, Long passportId);
}
