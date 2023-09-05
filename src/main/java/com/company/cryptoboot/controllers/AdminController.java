package com.company.cryptoboot.controllers;

import com.company.cryptoboot.services.MoneysService;
import com.company.cryptoboot.entities.User;
import com.company.cryptoboot.services.UsersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UsersService userService;
    private final MoneysService moneysService;

    public AdminController(UsersService userService, MoneysService moneysService) {
        this.userService = userService;
        this.moneysService = moneysService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUsers(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/transaction/{id}")
    public String transactionMoney(@PathVariable(value = "id") Long id) {
        return "redirect:/admin";
    }

    @GetMapping("/user")
    @ResponseBody
    public User userData(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/current")
    public String current(Model model) {
        model.addAttribute("allMoney", moneysService.findAllMoney());
        return "current";
    }
}
