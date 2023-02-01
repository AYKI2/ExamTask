package org.example.services;

import org.example.entity.Bank;
import org.example.repository.BankRepository;
import org.example.repository.BankRepositoryImpl;

import java.util.List;

public class BankServiceImpl implements BankService {
    private BankRepository bankRepository = new BankRepositoryImpl();
    public String saveBank(Bank bank, Long regionId) {
        bankRepository.saveBank(bank, regionId);
        return "Successfully saved!";
    }

    public String deleteBank(Long bankId) {
        bankRepository.deleteBank(bankId);
        return "Successfully deleted!";
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.getAllBanks();
    }

    public List<Bank> getBanksByRegionName(String regionName) {
        return bankRepository.getBanksByRegionName(regionName);
    }

    @Override
    public String assignBankToClient(Long clientId, Long bankId) {
        bankRepository.assignBankToClient(clientId,bankId);
        return "Assign success!";
    }
}
