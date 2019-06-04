package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;

public class AccountMapRepository implements AccountRepository{
	
	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
	
	//You must provide concrete implementation for each of these methods
	//do not change the method signature
	//THINK - if the parameter is a String, or the return type is a String
	//How can I convert to a String from an Object?
	//What utility methods do I have available?
	
	//You must complete this section using TDD
	//You can use the suggested tests or build your own.

	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public String createAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateAccount(int accountNumber, String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
