package org.example.services;

import org.example.entity.Passport;

import java.util.List;

public interface PassportService {
    String savePassport(Passport passport);
    String deleteAllPassports(Long passportId);
    List<Passport> getAllPassports();
    String assignPassportToClient(Long clientId, Long passportId);
}
