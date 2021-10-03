package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class ProfileController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/profile")
    public String home(Model model) {
        User user = userS.getUser("max@email.com");
        model.addAttribute("user", user);
        return "profile";
    }
}
