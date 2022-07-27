package com.escandell.bank2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.escandell.bank2.persistence.entity.Account;
import com.escandell.bank2.persistence.repository.AccountRepository;

@Service
public class AccountService {
    
	 private final AccountRepository repository;

	public AccountService(AccountRepository repository) {

		this.repository = repository;
	}
	 
	public Account CreatedAccount(Account account) {
		return this.repository.save(account);
	
	}
	public List<Account> ListAllAccount(){
		return this.repository.findAll();
	}
	public void DeleteAccountById(Long id) {
		this.repository.deleteById(id);
		
	}
	public Account GetAccountById(Long id) {
		return this.repository.findById(id).get();
		
	}
}
