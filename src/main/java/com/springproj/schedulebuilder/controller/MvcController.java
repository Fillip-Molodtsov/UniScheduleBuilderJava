package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.model.domain.user.AppUser;
import com.springproj.schedulebuilder.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/public")
public class MvcController {

    private IUserService iUserService;

    @GetMapping("/greeting")
    public String greeting(){
        return "greetings";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/sign-up-done")
    public String signUpDone(@ModelAttribute AppUser appUser, Model model) {
        try {
            iUserService.signUp(appUser);
            return "result";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }

    }
}
