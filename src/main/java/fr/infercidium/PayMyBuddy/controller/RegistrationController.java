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

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserI userS;

    @Autowired
    private UserMapper userM;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @PostMapping
    public String userRegistration(@ModelAttribute("user")UserRegistrationDto registrationDto) {
        //Gestion Error
        if (!registrationDto.getPassword().equals(registrationDto.getPassword2())) {
            return "redirect:/login?errorPassword";
        }

        //Mappage
        User user = userM.dtoToModel(registrationDto);

        //Service
        userS.postUser(user);

        //Return
        return "redirect:/home?registration";
    }
}
