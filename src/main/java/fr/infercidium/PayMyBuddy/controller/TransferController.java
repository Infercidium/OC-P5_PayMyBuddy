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
public class TransferController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/transfer")
    public String transfer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        model.addAttribute("connexion", user.getKnowUser());
        model.addAttribute("debitedHistory", user.getHistoryDebited());
        return "transfer";
    }
}
