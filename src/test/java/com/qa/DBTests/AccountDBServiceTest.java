package com.qa.DBTests;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountDBServiceTest {
	
	JSONUtil jsonutil;
	AccountDBRepository testADBR;
	String testJSON1 = "{\"accountNumber\":10,\"firstName\":\"Owen\",\"lastName\":\"Miller\"}";
	String testJSON2 = "{\"accountNumber\":11,\"firstName\":\"John\",\"lastName\":\"Smith\"}";
	String testJSON3 = "{\"accountNumber\":12,\"firstName\":\"Owen\",\"lastName\":\"Smith\"}";
	Account testAccount = new Account(1, 10, "Owen", "Miller");

	@Before
	public void setup() {

		jsonutil = new JSONUtil();
		testADBR = new AccountDBRepository();
	}
	
	
	@Test
	public void updateAnAccountTest() {
		
		testADBR.createAccount(testJSON1);
		
		testADBR.updateAccount(10, testJSON2);
		
		
		
		
		
		
	}

}
