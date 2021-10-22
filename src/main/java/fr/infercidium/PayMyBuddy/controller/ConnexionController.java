package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
public class ConnexionController {

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserI userS;

    @GetMapping(value = "/remove{id}")
    public String removecontact(@PathVariable Long id) {
        //Component
        User user = userComponent.saveUser();

        User removed = userS.getUser(id);

        user.removeKnowUser(removed);
        removed.removeKnowUser(user);

        userS.updateUser(user);
        userS.updateUser(removed);

        userComponent.cleanUser();
        return "redirect:/contact?remove";
    }

    @PostMapping(value = "/addCoC")
    public String addcontactC(String email) {
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

        userS.updateUser(user);
        userS.updateUser(added);

        userComponent.cleanUser();
        return "redirect:/contact?addCo";
    }

    @PostMapping(value = "/addCoT")
    public String addcontactT(String email) {
        //Component
        User user = userComponent.saveUser();

        if (userS.getUser(email) == null) {
            return "redirect:/transfer?addMiss";
        }
        User added = userS.getUser(email);
        if (user.getKnowUser().contains(added) || user.getEmail().equals(added.getEmail())) {
            return "redirect:/transfer?addError";
        }
        user.addKnowUser(added);
        added.addKnowUser(user);
        userS.updateUser(user);
        userS.updateUser(added);

        userComponent.cleanUser();
        return "redirect:/transfer?addCo";
    }
}
