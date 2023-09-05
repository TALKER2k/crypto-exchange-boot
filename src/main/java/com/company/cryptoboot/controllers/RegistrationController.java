package com.company.cryptoboot.controllers;

import com.company.cryptoboot.entities.User;
import com.company.cryptoboot.services.UsersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UsersService userService;

    @GetMapping()
    public String index(@AuthenticationPrincipal User user, Model model) {
        if (user != null)
            model.addAttribute("userPasswordKey", user.getPassword());
        return "index";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}
