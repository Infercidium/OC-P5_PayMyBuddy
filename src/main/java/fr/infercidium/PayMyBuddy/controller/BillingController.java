package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.Constants.PageConstant;
import fr.infercidium.PayMyBuddy.model.Billing;
import fr.infercidium.PayMyBuddy.service.BillingI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BillingController {

    /**
     * Instantiation of billingInterface.
     */
    @Autowired
    private BillingI billingS;

    /**
     * Generates the elements of the html page.
     * @param model is used to send the elements to the html page.
     * @param page indicates the number of the page to be generated and sent.
     * @return the Home html page.
     */
    @GetMapping(value = {"/billing"})
    public String billing(final Model model,
                       @RequestParam(defaultValue = "1") final int page) {

        // Creation of the Pagination
        Pageable pageable = PageRequest
                .of(page - 1, PageConstant.BILLINGPAGE,
                        Sort.by("transfer.dateTime").descending());
        Page<Billing> billingPage
                = billingS.getBillingPage(pageable);

        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < billingPage.getTotalPages(); i++) {
            pagecount.add(i + 1);
        }

        // Setting up the Pagination
        model.addAttribute("billing", billingPage.getContent());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", billingPage.getNumber());
        model.addAttribute("currentPage", billingPage.getNumber() + 1);
        model.addAttribute("next", billingPage.getNumber() + 2);

        // Referral to the affected html page
        return "billing";
    }
}
