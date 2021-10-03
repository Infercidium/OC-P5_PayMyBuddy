package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public void editTransfer(final Long id, final Transfer transfer) {
        Transfer verifiedTransfer = transferVerification(id, transfer);
        postTransfer(verifiedTransfer);
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

    private Transfer transferVerification(final Long id, final Transfer transfer) {
        Transfer origineTransfer = getTransfer(id);
        transfer.setId(origineTransfer.getId());
        if (transfer.getAmount() == null) {
            transfer.setAmount(origineTransfer.getAmount());
        }
        if (transfer.getCredited() == null) {
            transfer.setCredited(origineTransfer.getCredited());
        }
        if (transfer.getDebited() == null) {
            transfer.setDebited(origineTransfer.getDebited());
        }
        if (transfer.getDateTime() == null) {
            transfer.setDateTime(origineTransfer.getDateTime());
        }
        if (transfer.getDescription() == null) {
            transfer.setDescription(origineTransfer.getDescription());
        }
        LOGGER.debug("Verification of Transfer fields");
        return transfer;
    }
}
