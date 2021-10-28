package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Billing;
import fr.infercidium.PayMyBuddy.repository.BillingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillingService implements BillingI {

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(BillingService.class);

    /**
     * Instantiation of billingRepository.
     */
    @Autowired
    private BillingRepository billingR;

    /**
     * All billing list and put them into pages.
     * @param pageable used by Repository.
     * @return multi-page Billing list found.
     */
    @Override
    public Page<Billing> getBillingPage(final Pageable pageable) {
        LOGGER.debug("Billing Page");
        return billingR.findAll(pageable);
    }

    /**
     * Save a Billing.
     * @param billing to save.
     */
    @Override
    public void postBilling(final Billing billing) {
        billingR.save(billing);
        LOGGER.debug("Creation of the Billing");
    }
}
