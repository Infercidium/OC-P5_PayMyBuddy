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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/transfer")
public class TransferController {

    @Autowired
    private UserI userS;

    @GetMapping
    public String transfer(Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        List<Transfer> transferList = new ArrayList<>(user.getHistoryDebited()).stream().sorted(Comparator.comparing(Transfer::getDateTime)).collect(Collectors.toList());
        PagedListHolder transferPage = new PagedListHolder(transferList);
        transferPage.setPageSize(3);
        transferPage.setPage(page-1);
        List<Integer> pagecount = new ArrayList<>();
        for (int i = 0; i<transferPage.getPageCount(); i++) {
            pagecount.add(i+1);
        }
        model.addAttribute("connexion", user.getKnowUser().stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList()));
        model.addAttribute("debitedHistory", transferPage.getPageList());
        model.addAttribute("pageCount", pagecount);
        model.addAttribute("previous", transferPage.getPage());
        model.addAttribute("currentPage", transferPage.getPage()+1);
        model.addAttribute("next", transferPage.getPage()+2);
        return "transfer";
    }

    @PostMapping(value = "/addCo")
    public String addcontact(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userS.getUser(currentPrincipalName);
        if (userS.getUser(email) == null) {
            return "redirect:/transfer?addMiss";
        }
        User added = userS.getUser(email);
        if (user.getKnowUser().contains(added) || user.getEmail() == added.getEmail()) {
            return "redirect:/transfer?addError";
        }
        user.addKnowUser(added);
        added.addKnowUser(user);
        userS.editUser(currentPrincipalName, user);
        userS.editUser(added.getEmail(), added);
        return "redirect:/transfer?addCo";
    }
}
