package com.escandell.bank2.controller;

import com.escandell.bank2.persistence.repository.AccountRepository;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
     private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }


}
