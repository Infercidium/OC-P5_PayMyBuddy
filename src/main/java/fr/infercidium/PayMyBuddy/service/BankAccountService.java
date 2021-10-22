package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankAccountService implements BankAccountI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);

    @Autowired
    private BankAccountRepository bankAccountR;

    @Autowired
    private UserI userS;

    //Service Page BankAccount
    @Override
    public void creatBankAccount(final BankAccount bankAccount, final User user) {
        bankAccount.setUser(user);
        bankAccount.setDeer(bankAccount.getCardNumber().substring(bankAccount.getCardNumber().length() - 2));
        bankAccount.setCardNumber(BCrypt.hashpw(bankAccount.getCardNumber(), BCrypt.gensalt()));
        bankAccount.setCryptogram(BCrypt.hashpw(bankAccount.getCryptogram(), BCrypt.gensalt()));

        if (getBankAccount(bankAccount.getCardNumber()) != null) {
            bankAccount.setId(getBankAccount(bankAccount.getCardNumber())); //TODO Verifier le fonctionnement
        }

        postBankAccount(bankAccount);
        user.addBankAccount(bankAccount);
        LOGGER.info("BankAccount created and linked to the user");
    }

    @Override
    public void removeBankAccount(final Long id, final User user) {
        BankAccount bankAccount = getBankAccount(id);

        user.removeBankAccount(bankAccount);
        bankAccount.setUser(null);

        userS.updateUser(user);
        postBankAccount(bankAccount);
        LOGGER.info("Deletion of the linked bankAccount");
    }

    //Service
    @Override
    public void postBankAccount(final BankAccount bankAccount) {
        bankAccountR.save(bankAccount);
        LOGGER.debug("BankAccount save");
    }

    @Override
    public BankAccount getBankAccount(final Long id) {
        LOGGER.debug("BankAccount found");
        return bankAccountR.getById(id);
    }

    @Override
    public Long getBankAccount(final String cardNumber) {
        BankAccount bankAccount = bankAccountR.findByCardNumber(cardNumber);
        LOGGER.debug("BankAccount found");
        return bankAccount.getId();
    }

    //Pagination
    @Override
    public List<BankAccount> getUserBankAccount(final String email) {
        LOGGER.debug("BankAccounts of the user found");
        return bankAccountR.findByUserEmailIgnoreCase(email);
    }

    @Override
    public Page<BankAccount> getBankAccountPageUser(final String email, final Pageable pageable) {
        LOGGER.debug("User Generated BankAccounts Page");
        return bankAccountR.findByUserEmailIgnoreCase(email, pageable);
    }
}
