package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransferI {
    void postTransfer(Transfer transfer);

    Page<Transfer> getTransferPageCredited(String email, Pageable pageable);

    Page<Transfer> getTransferPageDebited(String email, Pageable pageable);

    List<Transfer> getTransferCredited(String email);

    List<Transfer> getTransferDebited(String email);
}
