package com.company.cryptoboot.controllers;

import com.company.cryptoboot.entities.User;
import com.company.cryptoboot.services.MoneysService;
import com.company.cryptoboot.services.UsersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;


@Controller
public class WalletController {
    private final UsersService userService;
    private final MoneysService moneysService;

    public WalletController(UsersService userService, MoneysService moneysService) {
        this.userService = userService;
        this.moneysService = moneysService;
    }

    @GetMapping("/wallet")
    public String wallet(Model model) {
        String password = "";
        model.addAttribute("password", password);
        return "wallet";
    }

    @GetMapping("/wallet/info/{id}")
    public String infoUserWallet(Model model, @PathVariable(value = "id") Long id) {
        User userWallet = userService.findUserById(id);
        model.addAttribute("userWallet", userWallet);
        return "info-wallet";
    }

    @PostMapping("/wallet/info")
    public String infoWallet(@RequestParam(value = "password") String password) {
        return "redirect:/wallet/info/" + userService.findByPassword(password).getId();
    }

    @PostMapping("/wallet/transact")
    public String transactRate(@AuthenticationPrincipal User user, @RequestParam(value = "walletAmount") double walletAmount, @RequestParam(name = "walletIn") String walletIn, @RequestParam(name = "walletOut") String walletOut) {
        if (moneysService.transactMoney(user.getId(), walletAmount, walletIn.toUpperCase(), walletOut.toUpperCase()))
            return "redirect:/wallet/info/" + user.getId();
        else return "redirect:/noMoney";
    }

    @GetMapping("/wallet/deposit")
    public String depositWallet() {
        return "deposit";
    }

    @PostMapping("/wallet/deposit/user")
    public String depositWallets(@AuthenticationPrincipal User user, @RequestParam(value = "amount") double amount) {
        userService.depositForUser(user, amount);
        return "redirect:/wallet/info/" + user.getId();
    }

    @GetMapping("/wallet/withdraw")
    public String withdrawWallet() {
        return "withdraw";
    }

    @GetMapping("/noMoney")
    public String noMoneyWallet() {
        return "noMoney";
    }

    @PostMapping("/wallet/withdraw/user")
    public String withdrawWallets(Model model, @AuthenticationPrincipal User user, @RequestParam(value = "amount") double amount) {
        if (user.getMoneyRUB() < amount) {
            model.addAttribute("walletError", "Недостаточно средств");
            return "no_money";
        } else {
            userService.withdrawForUserRub(user, amount);
            return "redirect:/wallet/info/" + user.getId();
        }
    }
}
