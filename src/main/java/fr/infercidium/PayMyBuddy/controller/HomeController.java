package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.Constants.PageConstant;
import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.AuthorityI;
import fr.infercidium.PayMyBuddy.service.BankAccountI;
import fr.infercidium.PayMyBuddy.service.TransferI;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    /**
     * Instantiation of userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of TransferInterface.
     */
    @Autowired
    private TransferI transferS;

    /**
     * Instantiation of bankaccountInterface.
     */
    @Autowired
    private BankAccountI bankAccountS;

    /**
     * Instantiation of AuthorityInterface.
     */
    @Autowired
    private AuthorityI authorityS;

    /**
     * Generates the elements of the html page.
     * @param model is used to send the elements to the html page.
     * @param page indicates the number of the page to be generated and sent.
     * @return the Home html page.
     */
    @GetMapping(value = {"/", "/home"})
    public String home(final Model model,
                       @RequestParam(defaultValue = "1") final int page) {
        //Component
        userComponent.cleanUser();
        User user = userComponent.saveUser();

        // Creation of the Pagination
        Pageable pageable = PageRequest
                .of(page - 1, PageConstant.TRANSFERPAGE,
                        Sort.by("dateTime").descending());
        Page<Transfer> creditedPage
                = transferS.getTransferPageCredited(user.getEmail(), pageable);

        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < creditedPage.getTotalPages(); i++) {
            pagecount.add(i + 1);
        }

        // Set up the information for the page
        model.addAttribute("cards", bankAccountS
                .getUserBankAccount(user.getEmail()).stream()
                .sorted(Comparator.comparing(BankAccount::getName))
                .collect(Collectors.toList()));
        model.addAttribute("pay", user.getPay());
        model.addAttribute("role", user.getAuthorities()
                .contains(authorityS.getRole("admin")));

        // Setting up the Pagination
        model.addAttribute("creditedHistory", creditedPage.getContent());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", creditedPage.getNumber());
        model.addAttribute("currentPage", creditedPage.getNumber() + 1);
        model.addAttribute("next", creditedPage.getNumber() + 2);

        // Referral to the affected html page
        return "home";
    }
}
