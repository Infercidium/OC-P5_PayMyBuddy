package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.Constants.PageConstant;
import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.TransferI;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/transfer")
public class TransferController {

    /**
     * Instantiation of userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    /**
     * Instantiation of transferInterface.
     */
    @Autowired
    private TransferI transferS;

    /**
     * Generates the elements of the html page.
     * @param model is used to send the elements to the html page.
     * @param page indicates the number of the page to be generated and sent.
     * @return the Transfer html page.
     */
    @GetMapping
    public String transfer(final Model model,
                           @RequestParam(defaultValue = "1") final int page) {
        //Component
        User user = userComponent.saveUser();

        // Creation of the Pagination
        Pageable pageable = PageRequest
                .of(page - 1, PageConstant.TRANSFERPAGE,
                        Sort.by("dateTime").descending());
        Page<Transfer> debitedPage
                = transferS.getTransferPageDebited(user.getEmail(), pageable);

        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < debitedPage.getTotalPages(); i++) {
            pagecount.add(i + 1);
        }

        // Set up the information for the page
        model.addAttribute("connexion",
                userS.getKnowUser(user.getEmail()).stream()
                        .sorted(Comparator.comparing(User::getUserName))
                        .collect(Collectors.toList()));

        // Setting up the Pagination
        model.addAttribute("debitedHistory", debitedPage.getContent());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", debitedPage.getNumber());
        model.addAttribute("currentPage", debitedPage.getNumber() + 1);
        model.addAttribute("next", debitedPage.getNumber() + 2);

        // Referral to the affected html page
        return "transfer";
    }
}
