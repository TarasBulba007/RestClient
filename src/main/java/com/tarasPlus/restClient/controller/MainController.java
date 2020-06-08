package com.tarasPlus.restClient.controller;

import com.tarasPlus.restClient.model.User;
import com.tarasPlus.restClient.restService.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MainController {

    private UserRestService service;

    @Autowired
    public MainController(UserRestService service) {
        this.service = service;
    }

    @GetMapping({"/", "/login"})
    public String login(){
        return "login";
    }
}
