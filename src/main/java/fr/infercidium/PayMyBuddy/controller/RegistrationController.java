package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.mapper.UserMapper;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    /**
     * Instantiation of userMappage.
     */
    @Autowired
    private UserMapper userM;

    /**
     * Creation of the ModelAttribute capturing the html form.
     * @return the userRegistrationDto with the form values.
     */
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    /**
     * Allows you to create your account.
     * @param registrationDto : information.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping
    public String userRegistration(
            @ModelAttribute("user") final UserRegistrationDto registrationDto) {
        //Gestion Error
        if (!registrationDto.getPassword()
                .equals(registrationDto.getPassword2())) {
            return "redirect:/login?errorPassword";
        }

        if (userS.getUsers().stream().anyMatch(user -> registrationDto.getEmail()
                .equals(user.getEmail()))) {
            return "redirect:/login?emailUsed";
        }

        //Mappage
        User user = userM.dtoToModel(registrationDto);

        //Service
        userS.postUser(user);

        //Return
        return "redirect:/home?registration";
    }
}
