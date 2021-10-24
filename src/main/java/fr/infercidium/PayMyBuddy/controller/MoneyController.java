package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.Constants.MoneyConstant;
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

    /**
     * Instantiation of userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of transferInterface.
     */
    @Autowired
    private TransferI transferS;

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the transferAdd with the form values.
     */
    @ModelAttribute("transferAdd")
    public TransferAdd transferAdd() {
        return new TransferAdd();
    }

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the transferRemov with the form values.
     */
    @ModelAttribute("transferRemov")
    public TransferRemov transferRemov() {
        return new TransferRemov();
    }

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the transferUser with the form values.
     */
    @ModelAttribute("transferUser")
    public TransferUser transferUser() {
        return new TransferUser();
    }

    /**
     * Create a Transfer between a User and his bank account.
     * @param transferAdd the transfer with the form values.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/addmoney")
    public String addMoney(
            @ModelAttribute("transferAdd") final TransferAdd transferAdd) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (transferAdd.getAmount().equals(BigDecimal.ZERO)) {
            return "redirect:/home?noAmount";
        }

        //Service
        transferS.addCardMoney(transferAdd, user);

        //return
        return "redirect:/home?success";
    }

    /**
     * Create a Transfer between a User and his bank account.
     * @param transferRemov the transfer with the form values.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/removmoney")
    public String removMoney(@ModelAttribute("transferRemov")
                                 final TransferRemov transferRemov) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (user.getPay().compareTo(transferRemov.getAmount()) < 0) {
            return "redirect:/home?error";
        }

        if (transferRemov.getAmount().equals(BigDecimal.ZERO)) {
            return "redirect:/home?noAmount";
        }

        //Service
        transferS.removCardMoney(transferRemov, user);

        //Return
        return "redirect:/home?success";
    }

    /**
     * Create a Transfer between two Users.
     * @param transferUser the transfer with the form values.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/pay")
    public String pay(
            @ModelAttribute("transferUser") final TransferUser transferUser) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (user.getPay().compareTo(transferUser.getAmount()
                .multiply(BigDecimal
                        .valueOf(MoneyConstant.MONETISATION))) < 0) {
            return "redirect:/transfer?errorPay";
        }

        if (transferUser.getAmount().equals(BigDecimal.ZERO)) {
            return "redirect:/transfer?noAmount";
        }

        //Service
        transferS.transactMoney(transferUser, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/transfer?successPay";
    }
}
