package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.model.domain.user.AppUser;
import com.springproj.schedulebuilder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/user")
public class UserController {

    private IUserService iUserService;

    @Autowired
    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/sign-up")
    void signUp(@RequestBody AppUser user) {
        iUserService.signUp(user);
    }
}
