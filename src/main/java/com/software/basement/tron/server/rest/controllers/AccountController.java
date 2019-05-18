package com.software.basement.tron.server.rest.controllers;


import com.software.basement.tron.server.account.Account;
import com.software.basement.tron.server.game.Lobby;
import com.software.basement.tron.server.game.Room;
import com.software.basement.tron.server.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountsRepository;

    @GetMapping("/{accountId}/{hashedPassword}")
    public Account getAccount(@PathVariable("accountId") ObjectId accountId,
                              @PathVariable("hashedPassword") String hashedPassword) {
        Account account = accountsRepository.findById(accountId);
        if(account.getHashedPassword().equals(hashedPassword))
            return accountsRepository.findById(accountId);
        else return null;
    }




}
