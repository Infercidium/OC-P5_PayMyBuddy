package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.mapper.UserMapper;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userS;
    private UserMapper userM;

    public RegistrationController(final UserService userSe, final UserMapper userMa) {
        this.userS = userSe;
        this.userM = userMa;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @PostMapping
    public String userRegistration(@ModelAttribute("user")UserRegistrationDto registrationDto) {
        User user = userM.dtoToModel(registrationDto);
        userS.postUser(user);
        return "redirect:/home?registration";
    }
}
