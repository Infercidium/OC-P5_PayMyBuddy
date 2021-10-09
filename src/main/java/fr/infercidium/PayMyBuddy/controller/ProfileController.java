package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.BankAccount;
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
public class ProfileController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/profile")
    public String profile(Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        List<BankAccount> cardList = new ArrayList<>(user.getBankAccounts().stream().sorted(Comparator.comparing(BankAccount::getName)).collect(Collectors.toList()));
        PagedListHolder bankPage = new PagedListHolder(cardList);
        bankPage.setPageSize(2);
        bankPage.setPage(page-1);
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i<bankPage.getPageCount(); i++) {
            pagecount.add(i+1);
        }
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("debitedHistorySize", user.getHistoryDebited().size());
        model.addAttribute("creditedHistorySize", user.getHistoryCredited().size());
        model.addAttribute("connexionSize", user.getKnowUser().size());
        model.addAttribute("cards", bankPage.getPageList());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", bankPage.getPage());
        model.addAttribute("currentPage", bankPage.getPage()+1);
        model.addAttribute("next", bankPage.getPage()+2);
        return "profile";
    }
}
