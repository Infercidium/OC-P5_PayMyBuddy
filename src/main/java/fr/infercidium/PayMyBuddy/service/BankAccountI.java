package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankAccountI {
    void postBankAccount(BankAccount bankAccount);

    BankAccount getBankAccount(Long id);

    List<BankAccount> getUserBankAccount(String email);

    Page<BankAccount> getBankAccountPageUser(String email, Pageable pageable);
}
