package com.software.basement.tron.server.rest.controllers;


import com.software.basement.tron.server.account.Account;
import com.software.basement.tron.server.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private int playersIdCounter = 0;
    @Autowired
    AccountRepository accountsRepository;

    @GetMapping("/{username}/{password}")
    public Account getAccount(@PathVariable("username") String username, @PathVariable("password") String password) {
        return accountsRepository.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountsRepository.findAll();
    }

    @PostMapping("/")
    public Account createAccount(@Valid @RequestBody Account account) {
        if (accountsRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()) == null) {
            account.setId(ObjectId.get());
            account.setWonGames(0);
            account.setPlayerId(playersIdCounter);
            playersIdCounter++;
            accountsRepository.save(account);
        }
        return account;
    }

    @PutMapping("/wonGame/{username}/{password}")
    public Integer incrementWonGames(@PathVariable("username") String username, @PathVariable("password") String password) {
        Account account = accountsRepository.findByUsernameAndPassword(username, password);
        if (account != null) account.setWonGames(account.getWonGames() + 1);
        accountsRepository.save(account);
        return account.getWonGames();
    }


}
