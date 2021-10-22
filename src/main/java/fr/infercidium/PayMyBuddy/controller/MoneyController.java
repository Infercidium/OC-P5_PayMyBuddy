package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.TransferAdd;
import fr.infercidium.PayMyBuddy.model.TransferRemov;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.TransferI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class MoneyController {

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private TransferI transferS;

    @ModelAttribute("transferAdd")
    public TransferAdd transferAdd() {
        return new TransferAdd();
    }

    @ModelAttribute("transferRemov")
    public TransferRemov transferRemov() {
        return new TransferRemov();
    }

    @ModelAttribute("TransferUser")
    public TransferUser transferUser() {
        return new TransferUser();
    }

    @PostMapping(value = "/addmoney")
    public String addMoney(@ModelAttribute("transferAdd") TransferAdd transferAdd) {
        //Component
        User user = userComponent.saveUser();

        //Service
        transferS.addCardMoney(transferAdd, user);

        //return
        return "redirect:/home?success";
    }

    @PostMapping(value = "/removmoney")
    public String removMoney(@ModelAttribute("transferRemov") TransferRemov transferRemov) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (user.getPay().compareTo(transferRemov.getAmount()) < 0) {
            return "redirect:/home?error";
        }

        //Service
        transferS.removCardMoney(transferRemov, user);

        //Return
        return "redirect:/home?success";
    }

    @PostMapping(value = "/pay")
    public String pay(@ModelAttribute("transferUser")TransferUser transferUser) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (user.getPay().compareTo((transferUser.getAmount().multiply(BigDecimal.valueOf(1.005)))) < 0) {
            return "redirect:/transfer?errorPay";
        }

        //Service
        transferS.transactMoney(transferUser, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/transfer?successPay";
    }
}
