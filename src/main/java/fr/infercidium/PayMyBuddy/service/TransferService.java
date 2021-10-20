package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService implements TransferI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    private final TransferRepository transfertR;

    public TransferService(final TransferRepository transfertRe) {
        this.transfertR = transfertRe;
    }

    @Override
    public Transfer postTransfer(final Transfer transfer) {
        return this.transfertR.save(transfer);
    }

    @Override
    public void removeTransfer(Long id) {
        Transfer transfer = getTransfer(id);
        if (transfer != null) {
            this.transfertR.delete(transfer);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Transfer getTransfer(Long id) {
        Optional<Transfer> transfer = this.transfertR.findById(id);
        if (transfer.isPresent()) {
            return transfer.get();
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transferList = this.transfertR.findAll();
        if (!transferList.isEmpty()) {
            return transferList;
        } else {
            throw new NullPointerException();
        }
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
