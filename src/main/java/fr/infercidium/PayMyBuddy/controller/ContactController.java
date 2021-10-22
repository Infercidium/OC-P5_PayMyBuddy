package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.User;
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
import java.util.List;

@Controller
@RequestMapping
public class ContactController {

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserI userS;

    @GetMapping(value = "/contact")
    public String contact(Model model, @RequestParam(defaultValue = "1") int page) {
        //Component
        User user = userComponent.saveUser();

        // Creation of the Pagination
        Pageable pageable = PageRequest.of((page - 1), 5, Sort.by("userName").ascending());
        Page<User> userPage = userS.getKnowUser(user.getEmail(), pageable);

        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < userPage.getTotalPages(); i++) {
            pagecount.add(i+1);
        }

        // Setting up the Pagination
        model.addAttribute("connexion", userPage.getContent());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", userPage.getNumber());
        model.addAttribute("currentPage", userPage.getNumber() + 1);
        model.addAttribute("next", userPage.getNumber() + 2);

        return "contact";
    }
}
