package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContactController {

    @Autowired
    private UserI userS;

    @GetMapping(value = "/contact")
    public String contact(Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        List<User> userList = new ArrayList<>(user.getKnowUser().stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList()));
        PagedListHolder contactPage = new PagedListHolder(userList);
        contactPage.setPageSize(5);
        contactPage.setPage(page-1);
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i<contactPage.getPageCount(); i++) {
            pagecount.add(i+1);
        }
        model.addAttribute("connexion", contactPage.getPageList());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", contactPage.getPage());
        model.addAttribute("currentPage", contactPage.getPage()+1);
        model.addAttribute("next", contactPage.getPage()+2);
        return "contact";
    }
}
