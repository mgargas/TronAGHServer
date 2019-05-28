package com.software.basement.tron.server.rest.controllers;


import com.software.basement.tron.server.account.Account;
import com.software.basement.tron.server.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
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

        Account queryResult = accountsRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (queryResult == null) {
            account.setId(ObjectId.get());
            account.setWins(0);
            Integer newId = accountsRepository.findAll(new Sort(Sort.Direction.DESC, "playerId"))
                    .stream().findFirst().map(a-> a.getPlayerId() + 1).orElse(0);
            account.setPlayerId(newId);
            accountsRepository.save(account);
            return account;
        }
        return queryResult;
    }

    @PutMapping("/wins/{username}/{password}")
    public Integer incrementWonGames(@PathVariable("username") String username, @PathVariable("password") String password) {
        Account account = accountsRepository.findByUsernameAndPassword(username, password);
        if (account != null) account.setWins(account.getWins() + 1);
        accountsRepository.save(account);
        return account.getWins();
    }


}
