package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnexionController {

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserI userS;

    @GetMapping(value = "/remove{id}")
    public String removecontact(@PathVariable final Long id) {
        //Component
        User user = userComponent.saveUser();

        //Service
        userS.removeConnection(id, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/contact?remove";
    }

    @PostMapping(value = "/addCoC")
    public String addcontactC(final String email) {
        //Component
        User user = userComponent.saveUser();


        //Gestion Error
        if (userS.getUser(email) == null) {
            return "redirect:/contact?addMiss";
        }

        if (user.getKnowUser().contains(userS.getUser(email))
                || user.getEmail().equals(userS.getUser(email).getEmail())) {
            return "redirect:/contact?addError";
        }

        //Service
        userS.addConnection(email, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/contact?addCo";
    }

    @PostMapping(value = "/addCoT")
    public String addcontactT(final String email) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (userS.getUser(email) == null) {
            return "redirect:/transfer?addMiss";
        }

        if (user.getKnowUser().contains(userS.getUser(email))
                || user.getEmail().equals(userS.getUser(email).getEmail())) {
            return "redirect:/transfer?addError";
        }

        //Service
        userS.addConnection(email, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/transfer?addCo";
    }
}
