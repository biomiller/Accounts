package com.qa.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;

@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil jsonutil;

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM Account a", Account.class);
		return util.getJSONForObject(query);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = jsonutil.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been successfully added\"}";
	}

	@Override
	public String deleteAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String account) {

		Query query = manager.createQuery("Select a FROM Movie a WHERE a.accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);

		Account oldAcc = (Account) query.getSingleResult();

		Account newAcc = jsonutil.getObjectForJSON(account, Account.class);

		manager.remove(oldAcc);
		manager.persist(newAcc);


		return account;
	}

}
