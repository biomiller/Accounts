package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountService;

@Path("/Account")
public class AccountEndpoint {
	
	@Inject
	AccountService service;
	
	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@Path("/getAnAccount/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAnAccount(@PathParam("id") int id) {
		return service.getAnAccount(id);
	}
	
	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String createAccount(String account) {
		return service.createAccount(account);
	}
	
	@Path("/updateAccount")
	@POST
	@Produces({ "application/json" })
	public String updateAccount(int accountNumber, String account) {
		return service.updateAccount(accountNumber, account);
	}
	
	@Path("/deleteAccount/{accountNumber}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("accountNumber") int accountNumber) {
		return service.deleteAccount(accountNumber);
	}

}
