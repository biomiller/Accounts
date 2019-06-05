package com.qa.MapTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	JSONUtil jsonutil;
	AccountMapRepository testAMR;
	String testJSON1 = "{\"id\":1,\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
	String testJSON2 = "{\"id\":2,\"accountNumber\":11,\"firstName\":\"John\",\"lastName\":\"Smith\"}";
	String testJSON3 = "{\"id\":3,\"accountNumber\":12,\"firstName\":\"Owen\",\"lastName\":\"Smith\"}";
	Account testAccount = new Account(1, 10, "Owen", "Miller");

	@Before
	public void setup() {

		jsonutil = new JSONUtil();
		testAMR = new AccountMapRepository();
	}

	@Test
	public void addAccountTest() {

		testAMR.createAccount(testJSON1);

		assertTrue(1 == testAMR.getAccountMap().size());

	}

	@Test
	public void add2AccountsTest() {

		testAMR.createAccount(testJSON1);
		testAMR.createAccount(testJSON2);

		assertTrue(2 == testAMR.getAccountMap().size());
	}

	@Test
	public void removeAccountTest() {

		testAMR.createAccount(testJSON1);

		assertTrue(1 == testAMR.getAccountMap().size());

		testAMR.deleteAccount(10);

		assertTrue(0 == testAMR.getAccountMap().size());

	}

	@Test
	public void remove2AccountsTest() {

		testAMR.createAccount(testJSON1);
		testAMR.createAccount(testJSON2);

		assertTrue(2 == testAMR.getAccountMap().size());

		testAMR.deleteAccount(10);
		testAMR.deleteAccount(11);

		assertTrue(0 == testAMR.getAccountMap().size());
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {

		testAMR.createAccount(testJSON1);
		testAMR.createAccount(testJSON2);

		assertTrue(2 == testAMR.getAccountMap().size());

		testAMR.deleteAccount(10);
		testAMR.deleteAccount(12);

		assertTrue(1 == testAMR.getAccountMap().size());
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

		assertEquals("{}", testAMR.getAllAccounts());

	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		// For a later piece of functionality
		// fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		// For a later piece of functionality
		// fail("TODO");
	}

	@Test
	public void jacksonGetAllAccounts() throws IOException {
		testAMR.createAccountJackson(testJSON1);
		// System.out.println(testAMR.getAllAccountsJackson());

	}

	@Test
	public void jacksonAddAccountTest() throws JsonParseException, JsonMappingException, IOException {

		testAMR.createAccountJackson(testJSON1);

		assertTrue(1 == testAMR.getAccountMap().size());

	}

	@Test
	public void nameFinderTest() {

		testAMR.createAccount(testJSON1);

		assertEquals(1, testAMR.findByName("Owen"));

	}

	@Test
	public void nameFinderTwoAccountsOneMatch() {

		testAMR.createAccount(testJSON1);
		testAMR.createAccount(testJSON2);

		assertEquals(1, testAMR.findByName("Owen"));

	}

	@Test
	public void nameFinderTwoAccountsTwoMatch() {

		testAMR.createAccount(testJSON1);
		testAMR.createAccount(testJSON3);

		assertEquals(2, testAMR.findByName("Owen"));

	}

}
