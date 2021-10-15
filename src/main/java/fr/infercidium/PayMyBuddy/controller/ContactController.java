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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserI userS;

    @GetMapping
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

    @GetMapping(value = "/remove{id}")
    public String removecontact(@PathVariable Long id) {
        //Component
        User user = userComponent.saveUser();

        User removed = userS.getUser(id);
        user.removeKnowUser(removed);
        removed.removeKnowUser(user);
        userS.editUser(user.getEmail(), user);
        userS.editUser(removed.getEmail(), removed);
        userComponent.cleanUser();
        return "redirect:/contact?remove";
    }

    @PostMapping(value = "/addCo")
    public String addcontact(String email) {
        //Component
        User user = userComponent.saveUser();

        if (userS.getUser(email) == null) {
            return "redirect:/contact?addMiss";
        }
        User added = userS.getUser(email);
        if (user.getKnowUser().contains(added) || user.getEmail().equals(added.getEmail())) {
            return "redirect:/contact?addError";
        }
        user.addKnowUser(added);
        added.addKnowUser(user);
        userS.editUser(user.getEmail(), user);
        userS.editUser(added.getEmail(), added);
        userComponent.cleanUser();
        return "redirect:/contact?addCo";
    }
}
