package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.Constants.MoneyConstant;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransferService implements TransferI {

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(TransferService.class);

    /**
     * Instantiation of transferRepository.
     */
    @Autowired
    private TransferRepository transfertR;

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    //Service MoneyController

    /**
     * Adds money from bankAccount to the User.
     * @param transferAdd : transfer with bankAccount.
     * @param user : Affected user.
     */
    @Override
    public void addCardMoney(final Transfer transferAdd, final User user) {
        user.setPay(user.getPay().add(transferAdd.getAmount()));

        transferAdd.setCredited(user);
        postTransfer(transferAdd);
        user.addHistoryCredited(transferAdd);
        userS.updateUser(user);
        LOGGER.info("Successful transaction between User "
                + "and their bankAccount");
    }

    /**
     * Adds money from User to the bankAccount.
     * @param transferRemov : transfer with bankAccount.
     * @param user : Affected user.
     */
    @Override
    public void removCardMoney(final Transfer transferRemov, final User user) {
        user.setPay(user.getPay().subtract(transferRemov.getAmount()));

        transferRemov.setDebited(user);
        postTransfer(transferRemov);
        user.addHistoryDebited(transferRemov);
        userS.updateUser(user);
        LOGGER.info("Successful transaction between User "
                + "and their bankAccount");
    }

    /**
     * Transaction between user.
     * @param transferUser transfer with credited User.
     * @param user Affected debited User.
     */
    @Override
    public void transactMoney(final Transfer transferUser, final User user) {
        User credited = transferUser.getCredited();

        user.setPay(user.getPay().subtract(transferUser.getAmount()
                .multiply(BigDecimal.valueOf(MoneyConstant.MONETISATION))));
        credited.setPay(credited.getPay().add(transferUser.getAmount()));

        transferUser.setDebited(user);
        postTransfer(transferUser);
        user.addHistoryDebited(transferUser);
        credited.addHistoryCredited(transferUser);
        userS.updateUser(user);
        userS.updateUser(credited);
        LOGGER.info("Successful User Transaction");
    }

    //Service

    /**
     * Save a transfer.
     * @param transfer to save.
     */
    @Override
    public void postTransfer(final Transfer transfer) {
        transfertR.save(transfer);
        LOGGER.debug("Creation of the Transfer");
    }

    //Pagination

    /**
     * Use the user's email to find the Transfer Credited
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Transfer list found.
     */
    @Override
    public Page<Transfer> getTransferPageCredited(final String email,
                                                  final Pageable pageable) {
        LOGGER.debug("User Transfers Page");
        return transfertR.findByCreditedEmailIgnoreCase(email, pageable);
    }

    /**
     * Use the user's email to find the Transfer Debited
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Transfer list found.
     */
    @Override
    public Page<Transfer> getTransferPageDebited(final String email,
                                                 final Pageable pageable) {
        LOGGER.debug("User Transfers Page");
        return transfertR.findByDebitedEmailIgnoreCase(email, pageable);
    }

    /**
     * Use the user's email to find the Transfer Credited list.
     * @param email used.
     * @return a list of Transfer.
     */
    @Override
    public List<Transfer> getTransferCredited(final String email) {
        LOGGER.debug("List of User Transfers");
        return transfertR.findByCreditedEmailIgnoreCase(email);
    }

    /**
     * Use the user's email to find the Transfer Debited list.
     * @param email used.
     * @return a list of Transfer.
     */
    @Override
    public List<Transfer> getTransferDebited(final String email) {
        LOGGER.debug("List of User Transfers");
        return transfertR.findByDebitedEmailIgnoreCase(email);
    }
}
