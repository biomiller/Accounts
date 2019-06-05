package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {
	
	JSONUtil jsonutil;
	AccountMapRepository testAMP;
	String testJSON1 = "{\"id\":1,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
	String testJSON2 = "{\"id\":2,\"accountNumber\":11,\"firstName\":\"John\",\"lastName\":\"Smith\"}";
	Account testAccount = new Account(1,10, "Owen", "Miller");

	@Before
	public void setup() {	
		
		jsonutil = new JSONUtil();
		testAMP = new AccountMapRepository();
	}
	
	
	@Test
	public void addAccountTest() {
			
		testAMP.createAccount(testJSON1);
		
		assertTrue(1 == testAMP.getAccountMap().size());
		
	}
	
	@Test
	public void add2AccountsTest() {
				
		testAMP.createAccount(testJSON1);
		testAMP.createAccount(testJSON2);
		
		assertTrue(2 == testAMP.getAccountMap().size());
	}

	@Test
	public void removeAccountTest() {
			
		testAMP.createAccount(testJSON1);
		
		assertTrue(1 == testAMP.getAccountMap().size());
		
		testAMP.deleteAccount(10);
		
		assertTrue(0 == testAMP.getAccountMap().size());

	}
	
	@Test
	public void remove2AccountsTest() {
		
		testAMP.createAccount(testJSON1);
		testAMP.createAccount(testJSON2);
		
		assertTrue(2 == testAMP.getAccountMap().size());
		
		testAMP.deleteAccount(10);
		testAMP.deleteAccount(11);
		
		assertTrue(0 == testAMP.getAccountMap().size()); 
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		
		testAMP.createAccount(testJSON1);
		testAMP.createAccount(testJSON2);
		
		assertTrue(2 == testAMP.getAccountMap().size());
		
		testAMP.deleteAccount(10);
		testAMP.deleteAccount(12);
		
		assertTrue(1 == testAMP.getAccountMap().size());
	}
	
	@Test
	public void jsonStringToAccountConversionTest() {
			
		Account testJSONtoAccount = jsonutil.getObjectForJSON(testJSON1, Account.class);
		
		assertEquals("Account", testJSONtoAccount.getClass().getSimpleName());
				
	}


	@Test
	public void accountConversionToJSONTest() {
		
		
		String testAccountJSON = jsonutil.getJSONForObject(testAccount);
		
		assertEquals("{\"id\":1,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}", testAccountJSON);
				
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		
		assertEquals("{}", testAMP.getAllAccounts());
	
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		//For a later piece of functionality
		fail("TODO");	
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		//For a later piece of functionality
		fail("TODO");	
	}

}
