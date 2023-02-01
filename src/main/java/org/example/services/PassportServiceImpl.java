package org.example.services;

import org.example.entity.Passport;
import org.example.repository.PassportRepository;
import org.example.repository.PassportRepositoryImpl;

import java.util.List;

public class PassportServiceImpl implements PassportService {
    private PassportRepository passportRepository = new PassportRepositoryImpl();
    public String savePassport(Passport passport) {
        passportRepository.savePassport(passport);
        return "Successfully saved!";
    }

    public String deleteAllPassports(Long passportId) {
        passportRepository.deleteAllPassports(passportId);
        return "Successfully cleared!";
    }

    public String assignPassportToClient(Long clientId, Long passportId) {
        passportRepository.assignPassportToClient(clientId,passportId);
        return "Assign successfully!";
    }

    @Override
    public List<Passport> getAllPassports() {
        return passportRepository.getAllPassports();
    }
}
