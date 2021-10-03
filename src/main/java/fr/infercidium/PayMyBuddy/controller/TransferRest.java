package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.service.TransferI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RolesAllowed({"USER"})
@Controller
public class TransferRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferRest.class);

    private final TransferI transferS;

    public TransferRest(final TransferI transferSe) {
        this.transferS = transferSe;
    }

    @PostMapping(value = "/Transfer")
    public ResponseEntity<Void> createTransfer(@Valid @RequestBody final Transfer transfer) {
        Transfer postTransfer = transferS.postTransfer(transfer);

        URI locate = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postTransfer.getId(), postTransfer.getDescription())
                .toUri();

        LOGGER.info("Saving " + transfer.getDescription() + " in the Transfers table");
        return ResponseEntity.created(locate).build();
    }

    @PutMapping(value = "/Transfer/{id}")
    public ResponseEntity<Void> editTransfer(@PathVariable final Long id, @RequestBody final Transfer transfer) {
        transferS.editTransfer(id, transfer);
        LOGGER.info("Transfer " + id + " modification");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "Transfer/{id}")
    public ResponseEntity<Void> removeTransfer(@PathVariable final Long id) {
        transferS.removeTransfer(id);
        LOGGER.info("Transfer " + id + " deletion");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/Transfer/{id}")
    public Transfer getTransfer(@PathVariable final Long id) {
        Transfer transfer = transferS.getTransfer(id);
        LOGGER.info("Transfer found");
        return transfer;
    }

    @GetMapping(value = "/Transfers")
    public List<Transfer> getTransfers() {
        List<Transfer> transferList = transferS.getTransfers();
        LOGGER.info("List of Transfers displayed");
        return transferList;
    }
}
