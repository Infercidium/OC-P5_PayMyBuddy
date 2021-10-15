package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransferI {
    Transfer postTransfer(Transfer transfer);

    void editTransfer(Long id, Transfer transfer);

    void removeTransfer(Long id);

    Transfer getTransfer(Long id);

    List<Transfer> getTransfers();

    Page<Transfer> getTransferPageCredited(String email, Pageable pageable);

    Page<Transfer> getTransferPageDebited(String email, Pageable pageable);

    List<Transfer> getTransferCredited(String email);

    List<Transfer> getTransferDebited(String email);
}
