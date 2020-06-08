package com.tarasPlus.restClient.controller;

import com.tarasPlus.restClient.model.Role;
import com.tarasPlus.restClient.restService.UserRestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private UserRestService roleService;

    public AdminRestController(UserRestService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/all-roles")
    public Set<Role> userRoles() {
        return new HashSet<>(roleService.findAll());
    }
}