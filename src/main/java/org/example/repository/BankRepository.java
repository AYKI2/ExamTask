package org.example.repository;

import org.example.entity.Bank;

import java.util.List;

public interface BankRepository {
    void saveBank(Bank bank, Long regionId);
    void deleteBank(Long bankId);
    List<Bank> getAllBanks();
    List<Bank> getBanksByRegionName(String name);
    void assignBankToClient(Long clientId, Long bankId);
}
