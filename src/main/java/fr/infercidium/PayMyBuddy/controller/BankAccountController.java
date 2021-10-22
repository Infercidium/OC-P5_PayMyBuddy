package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.mapper.BankAccountMapper;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BankAccountI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountController {

    /**
     * Instantiating userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of bankAccountMapper.
     */
    @Autowired
    private BankAccountMapper bankAccountM;

    /**
     * Instantiation of bankAccountInterface.
     */
    @Autowired
    private BankAccountI bankAccountS;

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the bankAccountDto with the form values.
     */
    @ModelAttribute("bankAccount")
    public BankAccountDto bankAccountDto() {
        return new BankAccountDto();
    }

    /**
     * Creation of a BankAccount.
     * @param bankAccountDto : HTML form converted via
     *                       the ModelAttribute to bankAccountDto.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/newBank")
    public String newBankAccount(@ModelAttribute("bankAccount")
                                     final BankAccountDto bankAccountDto) {
        //Component
        User user = userComponent.saveUser();

        //Gestion error
        PasswordEncoder passwordEncoder
                = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(bankAccountDto.getPassword(),
                user.getPassword())) {
            return "redirect:/profile?errorOldPassword";
        }

        //Mappage
        BankAccount bankAccount = bankAccountM.dtoToModel(bankAccountDto);

        //Service
        bankAccountS.creatBankAccount(bankAccount, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/profile?successUpBank";
    }

    /**
     * Deletion of a BankAccount.
     * @param id of the bankAccount to be deleted.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @GetMapping(value = "/removeBank{id}")
    public String removebank(@PathVariable final Long id) {
        //Component
        User user = userComponent.saveUser();

        //Service
        bankAccountS.removeBankAccount(id, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/profile?successRemBank";
    }
}
