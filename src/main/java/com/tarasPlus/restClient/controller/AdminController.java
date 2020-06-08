package com.tarasPlus.restClient.controller;

import com.tarasPlus.restClient.model.Role;
import com.tarasPlus.restClient.model.User;
import com.tarasPlus.restClient.restService.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class AdminController {

    private UserRestService service;
    private Environment environment;

    @Autowired
    public AdminController(UserRestService service, Environment environment) {
        this.service = service;
        this.environment = environment;
    }

    @GetMapping("/admin")
    public String userList(@ModelAttribute("user") User user,
                           @ModelAttribute("message") String message,
                           Model model) {
        List<User> list = service.getAllUsers();
        model.addAttribute("list", list);
        model.addAttribute("allRoles", service.findAll());
        return "admin";
    }

    @PostMapping(value = "/admin/add")
    public String userAdd(@ModelAttribute("user") User newUser, Model model, @ModelAttribute("role") String role) {
        service.addUser(newUser);
       // model.addAttribute("message", environment.getRequiredProperty("invalidData"));
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String userDelete(@RequestParam("id") Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit_user")
    public String editUser(@ModelAttribute User user, Model model,
                           @RequestParam(value = "rolesId") Long[] rolesId) {
        Set<Role> roles = new HashSet<>();
        for (Long id : rolesId) {
            roles.add(service.findById(id));
        }

        if (service.updateUser(user, roles)) {
            return "redirect:/admin";
        } else {
            model.addAttribute("message", environment.getRequiredProperty("invalidData"));
        }
        return "redirect:/admin";
    }
}
