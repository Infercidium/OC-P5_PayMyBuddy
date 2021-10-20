package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService implements BankAccountI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    private BankAccountRepository bankAccountR;

    @Override
    public void postBankAccount(final BankAccount bankAccount) {
        bankAccountR.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccount(final Long id) {
        return bankAccountR.getById(id);
    }

    @Override
    public List<BankAccount> getUserBankAccount(final String email) {
        return bankAccountR.findByUserEmailIgnoreCase(email);
    }

    @Override
    public Page<BankAccount> getBankAccountPageUser(final String email, final Pageable pageable) {
        return bankAccountR.findByUserEmailIgnoreCase(email, pageable);
    }
}
