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

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(BankAccountService.class);

    /**
     * Instantiation of bankAccountRepository.
     */
    @Autowired
    private BankAccountRepository bankAccountR;

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    //Service Page BankAccount
    /**
     * create a BankAccount in DataBase.
     * @param bankAccount to save.
     * @param user linked.
     */
    @Override
    public void creatBankAccount(final BankAccount bankAccount,
                                 final User user) {
        bankAccount.setUser(user);
        bankAccount.setDeer(bankAccount.getCardNumber()
                .substring(bankAccount.getCardNumber().length() - 2));
        bankAccount.setCardNumber(BCrypt
                .hashpw(bankAccount.getCardNumber(), BCrypt.gensalt()));
        bankAccount.setCryptogram(BCrypt
                .hashpw(bankAccount.getCryptogram(), BCrypt.gensalt()));

        postBankAccount(bankAccount);
        user.addBankAccount(bankAccount);
        userS.updateUser(user);
        LOGGER.info("BankAccount created and linked to the user");
    }

    /**
     * delete a BankAccount in dataBase. (just delink user)
     * @param id of BankAccount.
     * @param user linked.
     */
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
    /**
     * Save a bankAccount.
     * @param bankAccount to save.
     */
    @Override
    public void postBankAccount(final BankAccount bankAccount) {
        bankAccountR.save(bankAccount);
        LOGGER.debug("BankAccount save");
    }

    /**
     * Find a bankAccount using its id.
     * @param id used.
     * @return bankAccount found.
     */
    @Override
    public BankAccount getBankAccount(final Long id) {
        LOGGER.debug("BankAccount found");
        return bankAccountR.getById(id);
    }

    //Pagination
    /**
     * Use the user's email to find the BankAccount list.
     * @param email used.
     * @return a list of bankAccount.
     */
    @Override
    public List<BankAccount> getUserBankAccount(final String email) {
        LOGGER.debug("BankAccounts of the user found");
        return bankAccountR.findByUserEmailIgnoreCase(email);
    }

    /**
     * Use the user's email to find the BankAccount
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page BankAccount list found.
     */
    @Override
    public Page<BankAccount> getBankAccountPageUser(
            final String email, final Pageable pageable) {
        LOGGER.debug("User Generated BankAccounts Page");
        return bankAccountR.findByUserEmailIgnoreCase(email, pageable);
    }
}
