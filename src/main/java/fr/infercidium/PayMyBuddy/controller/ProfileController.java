package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        User user = userS.getUser("test@T.fr");
        model.addAttribute("user", user);
        return "profile";
    }
}
