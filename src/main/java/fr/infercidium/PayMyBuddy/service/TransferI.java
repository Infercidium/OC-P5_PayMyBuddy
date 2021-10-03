package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;

import java.util.List;

public interface TransferI {
    Transfer postTransfer(Transfer transfer);

    void editTransfer(Long id, Transfer transfer);

    void removeTransfer(Long id);

    Transfer getTransfer(Long id);

    List<Transfer> getTransfers();
}
