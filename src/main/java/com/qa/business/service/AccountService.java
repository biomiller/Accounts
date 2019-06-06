package com.qa.business.service;

public interface AccountService {

	String getAllAccounts();
	String getAnAccount(int accountNumber);
	String createAccount(String account);
	String deleteAccount(int accountNumber);
	String updateAccount(int accountNumber, String account);
	
}
