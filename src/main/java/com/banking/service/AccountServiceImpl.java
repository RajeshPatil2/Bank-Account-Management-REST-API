package com.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InvalidAmountException;
import com.banking.model.Account;
import com.banking.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		Account savedAccount = accountRepository.save(account);
		System.out.println("Saved Account: " + savedAccount);
		return savedAccount;
	}

	@Override
	public Account getAccountById(Long id) {
		Optional<Account> account = accountRepository.findById(id);

		if (account.isPresent()) {
			return account.get();
		} else {
			throw new AccountNotFoundException("Account ID " + id + " not found");
		}
	}

	@Override
	public List<Account> getAllAccounts() {

		return accountRepository.findAll();
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = getAccountById(id);
		accountRepository.delete(account);
	}

	@Override
	public void deposit(Long id, double amount) {
		if (amount <= 0) {
			throw new InvalidAmountException("Deposit amount must be greater than zero");
		}

		Account account = getAccountById(id);
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
	}

}
