package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("debitedHistorySize", user.getHistoryDebited().size());
        model.addAttribute("creditedHistorySize", user.getHistoryCredited().size());
        model.addAttribute("connexionSize", user.getKnowUser().size());
        model.addAttribute("bank", user.getBankAccounts());
        return "profile";
    }
}
