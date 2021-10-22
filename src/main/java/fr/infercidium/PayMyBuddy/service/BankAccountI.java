package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankAccountI {
    //Service Page BankAccount
    void creatBankAccount(BankAccount bankAccount, User user);

    void removeBankAccount(Long id, User user);

    //Service
    void postBankAccount(BankAccount bankAccount);

    BankAccount getBankAccount(Long id);

    Long getBankAccount(String cardNumber);

    //Pagination
    List<BankAccount> getUserBankAccount(String email);

    Page<BankAccount> getBankAccountPageUser(String email, Pageable pageable);
}
