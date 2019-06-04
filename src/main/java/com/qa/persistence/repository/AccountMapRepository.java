package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	private Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
		


	JSONUtil jsonutil = new JSONUtil();
	
	//You must provide concrete implementation for each of these methods
	//do not change the method signature
	//THINK - if the parameter is a String, or the return type is a String
	//How can I convert to a String from an Object?
	//What utility methods do I have available?
	
	//You must complete this section using TDD
	//You can use the suggested tests or build your own.

	public String getAllAccounts() {
		
		return "All values; " + this.getAccountMap().values();

		//return null;
	}
	
	
	public String createAccount(String account) {
		
		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);
		
		this.getAccountMap().put(newAccount.getAccountNumber(), newAccount);
		
		return "Added new account with id " + newAccount.getId() + " and name " + newAccount.getFirstName() + " " + newAccount.getLastName();
	}

	public String deleteAccount(int accountNumber) {
		
		if(this.getAccountMap().containsKey(accountNumber)) {
			this.getAccountMap().remove(accountNumber);
			return "Account with account number " + accountNumber + " removed.";
		}
		
		else {
			return "Account does not exist";
		}
		
		
	}

	public String updateAccount(int accountNumber, String account) {
		Account newAccount = jsonutil.getObjectForJSON(account, Account.class);
		
		this.getAccountMap().put(newAccount.getAccountNumber(), newAccount);
		
		return "Account updated";
	}
	
	
	
	
	
	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Integer, Account> accountMap) {
		this.accountMap = accountMap;
	}

}
