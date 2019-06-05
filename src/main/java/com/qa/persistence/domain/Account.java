package com.qa.persistence.domain;

public class Account {
	
	//This class needs to have:
	//An id
	//An Account Number
	//A First Name
	//A last Name
	
    private int id;
    //private static int idCounter = 1; 
	
	private int accountNumber;
	private String firstName;
	private String lastName;
	
/*	public Account(int accountNumber, String firstName, String lastName) {
		this.id = idCounter++;
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}*/
	
	public Account(int id, int accountNumber, String firstName, String lastName) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
	

}
