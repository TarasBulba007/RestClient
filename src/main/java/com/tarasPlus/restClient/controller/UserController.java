package com.tarasPlus.restClient.controller;

import com.tarasPlus.restClient.model.User;
import com.tarasPlus.restClient.restService.UserRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class UserController {

    private UserRestService service;
    private Environment environment;

    @Autowired
    public UserController(UserRestService service, Environment environment) {
        this.service = service;
        this.environment = environment;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user", user);
        //  System.out.println("UserName: " + user.getUsername());
        return "userPage";
    }



    @GetMapping("/access_denied")
    public String accessDenied() {
        return "accessDeniedView";
    }

}
