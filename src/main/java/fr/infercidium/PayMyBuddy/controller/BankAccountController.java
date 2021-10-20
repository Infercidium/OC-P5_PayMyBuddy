package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.mapper.BankAccountMapper;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BankAccountI;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountController {

    @Autowired
    BankAccountMapper bankAccountM;

    @Autowired
    BankAccountI bankAccountS;

    @Autowired
    UserI userS;

    @ModelAttribute("bankAccount")
    public BankAccountDto bankAccountDto() {
        return new BankAccountDto();
    }

    @PostMapping(value = "/newBank")
    public String newBankAccount(@ModelAttribute("bankAccount") BankAccountDto bankAccountDto) {
        //Component
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        user = userS.getUser(currentPrincipalName);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(bankAccountDto.getPassword(), user.getPassword())) {
            return "redirect:/profile?errorOldPassword";
        }

        BCrypt bCrypt = new BCrypt();
        BankAccount bankAccount = bankAccountM.dtoToModel(bankAccountDto);
        bankAccount.setUser(user);
        bankAccount.setDeer(bankAccount.getCardNumber().substring(bankAccount.getCardNumber().length() - 2));
        /*bankAccount.setCardNumber();
        bankAccount.setCryptogram();*/
        bankAccountS.postBankAccount(bankAccount);
        return "redirect:/profile?successUpBank";
    }

    @PostMapping(value = "/updateBank")
    public String updateBankAccount(@ModelAttribute("bankAccount") BankAccountDto bankAccountDto) {
        //Component
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        user = userS.getUser(currentPrincipalName);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(bankAccountDto.getPassword(), user.getPassword())) {
            return "redirect:/profile?errorOldPassword";
        }

        BCrypt bCrypt = new BCrypt();
        BankAccount bankAccount = bankAccountM.dtoToModel(bankAccountDto);
        bankAccount.setUser(user);
        bankAccount.setDeer(bankAccount.getCardNumber().substring(bankAccount.getCardNumber().length() - 2));
        /*bankAccount.setCardNumber();
        bankAccount.setCryptogram();*/
        bankAccountS.postBankAccount(bankAccount);
        return "redirect:/profile?successBank";
    }

    @GetMapping(value = "/removeBank{id}")
    public String removebank(@PathVariable Long id) {
        //Component
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        user = userS.getUser(currentPrincipalName);

        BankAccount bankAccount = bankAccountS.getBankAccount(id);

        user.removeBankAccount(bankAccount);
        bankAccount.setUser(null);

        userS.updateUser(user);
        bankAccountS.postBankAccount(bankAccount);

        return "redirect:/profile?successRemBank";
    }
}
