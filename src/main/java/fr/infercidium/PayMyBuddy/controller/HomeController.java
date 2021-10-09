package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserI userS;

    @GetMapping(value = {"/", "/home"})
    public String home(final Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        List<Transfer> transferList = new ArrayList<>(user.getHistoryCredited()).stream().sorted(Comparator.comparing(Transfer::getDateTime)).collect(Collectors.toList());
        PagedListHolder transferPage = new PagedListHolder(transferList);
        transferPage.setPageSize(3);
        transferPage.setPage(page-1);
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i<transferPage.getPageCount(); i++) {
            pagecount.add(i+1);
        }
        model.addAttribute("card", user.getBankAccounts().stream().sorted(Comparator.comparing(BankAccount::getName)).collect(Collectors.toList()));
        model.addAttribute("pay", user.getPay());
        model.addAttribute("creditedHistory", transferPage.getPageList());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", transferPage.getPage());
        model.addAttribute("currentPage", transferPage.getPage()+1);
        model.addAttribute("next", transferPage.getPage()+2);
        return "home";
    }
}
