package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Inject
	AccountRepository repo;

	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String getAnAccount(int accountNumber) {
		return repo.getAnAccount(accountNumber);
	}

	@Override
	public String createAccount(String account) {

		if (account.contains("9999")) {
			return "{\"message\": \"This account is blocked\"}";
		}

		else {
			repo.createAccount(account);
			return "{\"message\": \"Account created\"}";
		}

	}

	@Override
	public String deleteAccount(int accountNumber) {
		return repo.deleteAccount(accountNumber);
	}

	@Override
	public String updateAccount(int accountNumber, String account) {
		return repo.updateAccount(accountNumber, account);
	}

	public AccountRepository getRepo() {
		return repo;
	}

}
