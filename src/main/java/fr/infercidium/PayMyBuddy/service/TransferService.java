package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService implements TransferI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferRepository transfertR;

    @Override
    public void postTransfer(final Transfer transfer) {
        transfertR.save(transfer);
    }

    @Override
    public Page<Transfer> getTransferPageCredited(final String email, final Pageable pageable) {
        return transfertR.findByCreditedEmailIgnoreCase(email, pageable);
    }

    @Override
    public Page<Transfer> getTransferPageDebited(final String email, final Pageable pageable) {
        return transfertR.findByDebitedEmailIgnoreCase(email, pageable);
    }

    @Override
    public List<Transfer> getTransferCredited(final String email) {
        return transfertR.findByCreditedEmailIgnoreCase(email);
    }

    @Override
    public List<Transfer> getTransferDebited(final String email) {
        return transfertR.findByDebitedEmailIgnoreCase(email);
    }
}
