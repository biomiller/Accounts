package com.qa.persistence.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;
import com.qa.util.JacksonJSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	@Alternative
	
	private Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
		


	JSONUtil jsonutil = new JSONUtil();
	
	JacksonJSONUtil jacksonjsonutil = new JacksonJSONUtil();
	
	//You must provide concrete implementation for each of these methods
	//do not change the method signature
	//THINK - if the parameter is a String, or the return type is a String
	//How can I convert to a String from an Object?
	//What utility methods do I have available?
	
	//You must complete this section using TDD
	//You can use the suggested tests or build your own.

	public String getAllAccounts() {
		
		
		String allAccounts = jsonutil.getJSONForObject(this.getAccountMap());
		
		return allAccounts;

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
		
		this.getAccountMap().put(accountNumber, newAccount);
		
		return "Account updated";
	}
	
	// Using Jackson
	
	public String getAllAccountsJackson() throws JsonProcessingException {
		
		return jacksonjsonutil.jacksonGetJSONForObject(this.getAccountMap());

	}
	
	
	public String createAccountJackson(String account) throws IOException {
		
		Account newAccount = jacksonjsonutil.jacksonGetObjectForJSON(account, Account.class);
				
		this.getAccountMap().put(newAccount.getAccountNumber(), newAccount);
		
		return "Added new account with id " + newAccount.getId() + " and name " + newAccount.getFirstName() + " " + newAccount.getLastName();
	}

	
	
	
	
	
	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Integer, Account> accountMap) {
		this.accountMap = accountMap;
	}


	public int findByName(String name) {
		int counter = 0;
		for (Account acc : this.getAccountMap().values()) {
			if(acc.getFirstName().equals(name)) {
				counter++;
			}
		}
		System.out.println("First Name " + name + " found " + counter + " time(s)");
		return counter;
	}

}
