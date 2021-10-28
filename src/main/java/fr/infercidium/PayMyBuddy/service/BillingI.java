package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillingI {

    /**
     * All billing find list and put them into pages.
     * @param pageable used by Repository.
     * @return multi-page Billing list found.
     */
    Page<Billing> getBillingPage(Pageable pageable);

    /**
     * Save a Billing.
     * @param billing to save.
     */
    void postBilling(Billing billing);
}
