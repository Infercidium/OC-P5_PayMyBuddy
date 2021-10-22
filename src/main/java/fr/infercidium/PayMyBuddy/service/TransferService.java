package fr.infercidium.PayMyBuddy.service;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferRepository transfertR;

    @Autowired
    private UserI userS;

    //Service MoneyController
    @Override
    public void addCardMoney(final Transfer transferAdd, final User user) {
        user.setPay(user.getPay().add(transferAdd.getAmount()));

        transferAdd.setCredited(user);
        postTransfer(transferAdd);
        user.addHistoryCredited(transferAdd);
        userS.updateUser(user);
        LOGGER.info("Successful transaction between User and their bankAccount");
    }

    @Override
    public void removCardMoney(final Transfer transferRemov, final User user) {
        user.setPay(user.getPay().subtract(transferRemov.getAmount()));

        transferRemov.setDebited(user);
        postTransfer(transferRemov);
        user.addHistoryDebited(transferRemov);
        userS.updateUser(user);
        LOGGER.info("Successful transaction between User and their bankAccount");
    }

    @Override
    public void transactMoney(final Transfer transferUser, User user) {
        User credited = transferUser.getCredited();

        user.setPay(user.getPay().subtract(transferUser.getAmount().multiply(BigDecimal.valueOf(1.005))));
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
    @Override
    public void postTransfer(final Transfer transfer) {
        transfertR.save(transfer);
        LOGGER.debug("Creation of the Transfer");
    }

    //Pagination
    @Override
    public Page<Transfer> getTransferPageCredited(final String email, final Pageable pageable) {
        LOGGER.debug("User Transfers Page");
        return transfertR.findByCreditedEmailIgnoreCase(email, pageable);
    }

    @Override
    public Page<Transfer> getTransferPageDebited(final String email, final Pageable pageable) {
        LOGGER.debug("User Transfers Page");
        return transfertR.findByDebitedEmailIgnoreCase(email, pageable);
    }

    @Override
    public List<Transfer> getTransferCredited(final String email) {
        LOGGER.debug("List of User Transfers");
        return transfertR.findByCreditedEmailIgnoreCase(email);
    }

    @Override
    public List<Transfer> getTransferDebited(final String email) {
        LOGGER.debug("List of User Transfers");
        return transfertR.findByDebitedEmailIgnoreCase(email);
    }
}
