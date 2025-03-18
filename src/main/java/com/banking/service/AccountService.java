package com.banking.service;

import java.util.List;

import com.banking.model.Account;

public interface AccountService {

	Account createAccount(Account account);

	Account getAccountById(Long id);

	List<Account> getAllAccounts();

	void deleteAccount(Long id);

	void deposit(Long id, double amount);

}
