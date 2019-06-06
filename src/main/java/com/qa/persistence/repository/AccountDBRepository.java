package com.qa.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.inject.Inject;

@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil jsonutil;
	
	
    public String findAccount(int ID) {
    return jsonutil.getJSONForObject(manager.find(Account.class, ID));
    }

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM Account a", Account.class);
		return jsonutil.getJSONForObject(query);
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
	public String deleteAccount(int id) {
		Account account = manager.find(Account.class, id);

        if (manager.contains(account)) {
        	manager.getTransaction().begin();
        	manager.remove(account);
        	manager.getTransaction().commit();
            return "{\"message\": \"Account sucessfully deleted " + id + " \"}";
        }

        return "{\"message\": \"No account found with this id.\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String account) {

		Query query = manager.createQuery("Select a FROM Account a WHERE a.accountNumber = :accountNumber");
		query.setParameter("accountNumber", accountNumber);

		Account oldAcc = (Account) query.getSingleResult();

		Account newAcc = jsonutil.getObjectForJSON(account, Account.class);

		manager.getTransaction().begin();
		manager.remove(oldAcc);
		manager.persist(newAcc);
		manager.getTransaction().commit();
		

		return "{\"message\": \"account has been successfully updated\"}";
	}

}
