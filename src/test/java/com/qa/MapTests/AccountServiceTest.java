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

	@Before
	public void setup() {	
		
		jsonutil = new JSONUtil();
	}
	
	
	@Test
	public void addAccountTest() {
		
		AccountMapRepository testAMP = new AccountMapRepository();
		
		String testJSON = "{\"id\":4,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
		
		testAMP.createAccount(testJSON);
		
		assertTrue(1 == testAMP.getAccountMap().size());
		
	}
	
	@Test
	public void add2AccountsTest() {
		
		AccountMapRepository testAMP = new AccountMapRepository();
		
		String testJSON1 = "{\"id\":1,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
		
		String testJSON2 = "{\"id\":2,\"accountNumber\":11,\"firstName\":\"John\",\"lastName\":\"Smith\"}";
		
		testAMP.createAccount(testJSON1);
		testAMP.createAccount(testJSON2);
		
		System.out.println(testAMP.getAccountMap().get(11).getId());

		
		assertTrue(2 == testAMP.getAccountMap().size());
	}

	@Test
	public void removeAccountTest() {
		fail("TODO");	
	}
	
	@Test
	public void remove2AccountsTest() {
		fail("TODO");	
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		fail("TODO");	
	}
	
	@Test
	public void jsonStringToAccountConversionTest() {
		
		String testJSON = "{\"id\":4,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
		
		Account testJSONtoAccount = jsonutil.getObjectForJSON(testJSON, Account.class);
		
		assertEquals("Account", testJSONtoAccount.getClass().getSimpleName());
				
	}


	@Test
	public void accountConversionToJSONTest() {
		
		AccountMapRepository testAMP = new AccountMapRepository();
		
		Account testAccount = new Account(1,10, "Owen", "Miller");
		
		String testAccountJSON = jsonutil.getJSONForObject(testAccount);
		
		assertEquals("{\"id\":1,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}", testAccountJSON);
				
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		//For a later piece of functionality
		fail("TODO");	
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
