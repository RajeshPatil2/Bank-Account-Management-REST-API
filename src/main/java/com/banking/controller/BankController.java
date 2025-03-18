package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.Account;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class BankController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/create")
	public ResponseEntity<Account> getAccount(@RequestBody Account account) {

		return ResponseEntity.ok(accountService.createAccount(account));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		return ResponseEntity.ok(accountService.getAccountById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return ResponseEntity.ok(accountService.getAllAccounts());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account delete sucessfully");
	}

	@PostMapping("/deposite")
	public ResponseEntity<String> deposit(@RequestBody Account account) {
		accountService.deposit(account.getId(), account.getBalance());
		return ResponseEntity.ok("Amount deposited successfully");
	}

}
