package org.example;

import org.example.config.HibernateConfig;
import org.example.entity.Bank;
import org.example.entity.Client;
import org.example.entity.Passport;
import org.example.entity.Region;
import org.example.services.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static BankService bankService = new BankServiceImpl();
    private static ClientService clientService = new ClientServiceImpl();
    private static PassportService passportService = new PassportServiceImpl();
    private static RegionService regionService = new RegionServiceImpl();
    public static void main( String[] args ) {
        HibernateConfig.getSession();
        boolean isTrue = true;
        while (isTrue){
            System.out.println("""
                    ----------MAIN----------
                    1 - Client
                    2 - Passport
                    3 - Bank
                    4 - Region
                    5 - Exit
                    """);
            int start = new Scanner(System.in).nextInt();
            switch (start){
                case 1 ->{
                    boolean isClient = true;
                    while (isClient){
                        System.out.println("""
                                ----------CLIENT----------
                                1 - Save Client
                                2 - Delete By ID
                                3 - Find By ID
                                4 - Get All
                                5 - Back
                                """);
                        int number1 = new Scanner(System.in).nextInt();
                        switch (number1){
                            case 1 -> System.out.println(clientService.saveClient(createClient()));
                            case 2 -> System.out.println(clientService.deleteById(scanId()));
                            case 3 -> System.out.println(clientService.findById(scanId()));
                            case 4 -> System.out.println(clientService.getAllClients());
                            case 5 -> isClient = false;
                            default -> System.out.println("No such number!");
                        }
                    }
                }
                case 2 -> {
                    boolean isPassport = true;
                    while (isPassport) {
                        System.out.println("""
                                ----------PASSPORT----------
                                1 - Save Passport
                                2 - Delete All Passports
                                3 - Assign Passport To Client
                                4 - Get All
                                5 - Back
                                """);
                        int number2 = new Scanner(System.in).nextInt();
                        switch (number2){
                            case 1 -> System.out.println(passportService.savePassport(createPassport()));
                            case 2 -> System.out.println(passportService.deleteAllPassports(scanId()));
                            case 3 -> {
                                System.out.println("Enter client ID: ");
                                Long clientId = new Scanner(System.in).nextLong();
                                System.out.println("Enter passport ID: ");
                                Long passportId = new Scanner(System.in).nextLong();
                                System.out.println(passportService.assignPassportToClient(clientId,passportId));
                            }
                            case 4 -> System.out.println(passportService.getAllPassports());
                            case 5 -> isPassport = false;
                            default -> System.out.println("No such number!");
                        }
                    }
                }
                case 3 -> {
                    boolean isBank = true;
                    while (isBank){
                        System.out.println("""
                                ----------BANK----------
                                1 - Save Bank
                                2 - Delete Bank
                                3 - Get Banks By Region Name
                                4 - Get All
                                5 - Assign Bank To Client
                                6 - Back
                                """);
                        int number3 = new Scanner(System.in).nextInt();
                        switch (number3){
                            case 1 -> System.out.println(bankService.saveBank(createBank(),scanId()));
                            case 2 -> System.out.println(bankService.deleteBank(scanId()));
                            case 3 -> {
                                System.out.println("Enter Region Name: ");
                                System.out.println(bankService.getBanksByRegionName(new Scanner(System.in).nextLine()));
                            }
                            case 4 -> System.out.println(bankService.getAllBanks());
                            case 5 -> {
                                System.out.println("Enter clientId: ");
                                Long clientId = new Scanner(System.in).nextLong();
                                System.out.println("Enter bankId: ");
                                Long bankId = new Scanner(System.in).nextLong();
                                System.out.println(bankService.assignBankToClient(clientId,bankId));
                            }
                            case 6 -> isBank = false;
                            default -> System.out.println("No such number!");
                        }
                    }
                }
                case 4 -> {
                    boolean isRegion = true;
                    while (isRegion){
                        System.out.println("""
                                ----------Region----------
                                1 - Save Region
                                2 - Get All Region
                                3 - Update
                                4 - Back
                                """);
                        int number3 = new Scanner(System.in).nextInt();
                        switch (number3){
                            case 1 -> System.out.println(regionService.saveRegion(createRegion()));
                            case 2 -> System.out.println(regionService.getAllRegions());
                            case 3 -> {
                                System.out.println("Enter RegionID: ");
                                Long regionID = new Scanner(System.in).nextLong();
                                System.out.println(regionService.update(createRegion(),regionID));
                            }
                            case 4 -> isRegion = false;
                            default -> System.out.println("No such number!");
                        }
                    }
                }
                case 5 -> isTrue = false;
                default -> System.out.println("No such number!");
            }
        }
    }
    public static Client createClient(){
        System.out.println("Enter Full Name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter Phone Number: ");
        String number = new Scanner(System.in).nextLine();
        return new Client(name, number);
    }
    public static Passport createPassport(){
        System.out.println("Enter INN: ");
        String Inn = new Scanner(System.in).nextLine();
        return new Passport(Inn);
    }
    public static Bank createBank(){
        System.out.println("Enter Bank Name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter Address: ");
        String address = new Scanner(System.in).nextLine();
        return new Bank(name, address);
    }
    public static Region createRegion(){
        System.out.println("Enter Region Name: ");
        String region = new Scanner(System.in).nextLine();
        return new Region(region);
    }
    public static Long scanId(){
        try {
            System.out.println("Enter id: ");
            Long id = new Scanner(System.in).nextLong();
            return id;
        }catch (InputMismatchException e){
            System.out.println("Insert the number!!!");
        }
        return null;
    }
}

