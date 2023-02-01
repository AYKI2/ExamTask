package org.example.services;

import org.example.entity.Bank;

import java.util.List;

public interface BankService {
    String saveBank(Bank bank, Long regionId);
    String deleteBank(Long bankId);
    List<Bank> getAllBanks();
    List<Bank> getBanksByRegionName(String name);
    String assignBankToClient(Long clientId, Long bankId);
}
