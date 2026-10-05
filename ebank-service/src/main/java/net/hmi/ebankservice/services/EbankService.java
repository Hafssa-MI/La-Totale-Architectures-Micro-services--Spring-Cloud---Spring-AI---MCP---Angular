package net.hmi.ebankservice.services;

import net.hmi.ebankservice.entities.BankAccount;
import net.hmi.ebankservice.feign.CustomerRestClient;
import net.hmi.ebankservice.model.Customer;
import net.hmi.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id){
        BankAccount bankAccount =  bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }

    public BankAccount save(BankAccount bankAccount){
        try{
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return bankAccountRepository.save(bankAccount);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
