package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.Constants.PageConstant;
import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BankAccountI;
import fr.infercidium.PayMyBuddy.service.TransferI;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    /**
     * Instantiation of userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    /**
     * Instantiation of transferInterface.
     */
    @Autowired
    private TransferI transferS;

    /**
     * Instantiation of bankAccountInterface.
     */
    @Autowired
    private BankAccountI bankAccountS;

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the userRegistrationDto with the form values.
     */
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    /**
     * Generates the elements of the html page.
     * @param model is used to send the elements to the html page.
     * @param page indicates the number of the page to be generated and sent.
     * @return the Profile html page.
     */
    @GetMapping
    public String profile(final Model model,
                          @RequestParam(defaultValue = "1") final int page) {
        //Component
        User user = userComponent.saveUser();

        // Creation of the Pagination
        Pageable pageable
                = PageRequest.of(page - 1,
                PageConstant.BANKPAGE, Sort.by("name").ascending());
        Page<BankAccount> bankPage = bankAccountS
                .getBankAccountPageUser(user.getEmail(), pageable);

        // Count the pages
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i < bankPage.getTotalPages(); i++) {
            pagecount.add(i + 1);
        }

        // Create the information for the page
        List<Transfer> transfersCredited
                = transferS.getTransferCredited(user.getEmail());
        List<Transfer> transfersDebited
                = transferS.getTransferDebited(user.getEmail());
        List<User> users = userS.getKnowUser(user.getEmail());

        // Set up the information for the page
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("connexionSize", users.size());
        model.addAttribute("creditedHistorySize", transfersCredited.size());
        model.addAttribute("debitedHistorySize", transfersDebited.size());

        // Setting up the Pagination
        model.addAttribute("cards", bankPage.getContent());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", bankPage.getNumber());
        model.addAttribute("currentPage", bankPage.getNumber() + 1);
        model.addAttribute("next", bankPage.getNumber() + 2);

        // Referral to the affected html page
        return "profile";
    }

    /**
     * Allows you to modify your account information.
     * @param registrationDto : new information.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/edit")
    public String editProfile(@ModelAttribute("user")
                                  final UserRegistrationDto registrationDto) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (!registrationDto.getPassword()
                .equals(registrationDto.getPassword2())) {
            return "redirect:/profile?errorPassword";
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(
                registrationDto.getOldPassword(), user.getPassword())) {
            return "redirect:/profile?errorOldPassword";
        }

        //Service
        userS.editUser(registrationDto, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/profile?success";
    }
}
