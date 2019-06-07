package com.qa.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil jsonutil;

	public String getAnAccount(int accountNum) {

		Query query = manager.createQuery("Select a FROM Account a WHERE a.accountNumber = :accountNum");
		query.setParameter("accountNum", accountNum);

		try {
			Account acc = (Account) query.getSingleResult();
			return jsonutil.getJSONForObject(acc);
		} catch (NoResultException e) {
			return "{\"message\": \"No account found with this id.\"}";
		}

	}

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM Account a", Account.class);
		return jsonutil.getJSONForObject(query.getResultList());
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = jsonutil.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been successfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(int accountNum) {

		Query query = manager.createQuery("Select a FROM Account a WHERE a.accountNumber = :accountNum");
		query.setParameter("accountNum", accountNum);

		try {
			Account acc = (Account) query.getSingleResult();
			manager.remove(acc);

			return "{\"message\": \"Account has been successfully updated\"}";
		} catch (NoResultException e) {
			return "{\"message\": \"No account found with this id.\"}";
		}
		/*
		 * Account account = manager.find(Account.class, id);
		 * 
		 * if (manager.contains(account)) { manager.remove(account);
		 * 
		 * return "{\"message\": \"Account sucessfully deleted " + id + " \"}"; }
		 * 
		 * return "{\"message\": \"No account found with this id.\"}";
		 */
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountNum, String account) {

		Query query = manager.createQuery("Select a FROM Account a WHERE a.accountNumber = :accountNum");
		query.setParameter("accountNum", accountNum);

		try {
			Account oldAcc = (Account) query.getSingleResult();

			Account newAcc = jsonutil.getObjectForJSON(account, Account.class);

			oldAcc.setAccountNumber(newAcc.getAccountNumber());
			oldAcc.setFirstName(newAcc.getFirstName());
			oldAcc.setAccountNumber(newAcc.getAccountNumber());
			
			manager.persist(oldAcc);

			return "{\"message\": \"Account has been successfully updated\"}";
		} catch (NoResultException e) {
			return "{\"message\": \"No account found with this id.\"}";

		}

	}

}
