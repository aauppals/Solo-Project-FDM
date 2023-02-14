package javaproject.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javaproject.models.UserAccount;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          The User Account Data Access Object (DB-Dao) is responsible
 *          for writing and reading data from the UserAccount DB, using the
 *          entity manager factory and entity manager (JPA).
 */
public class UserAccountDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public UserAccountDao() {
		super();
		this.emf = Persistence.createEntityManagerFactory("Java_Project");
		this.em = emf.createEntityManager();
	}

	public void addUserAccount(UserAccount useraccount) {
		em.getTransaction().begin();
		em.persist(useraccount);
		em.getTransaction().commit();
		em.close();
	}

	// find userAccount by Id
	public UserAccount getUserAccount(int id) {
		em.getTransaction().begin();
		UserAccount ua = em.find(UserAccount.class, id);
		em.close();
		return ua;
	}

	public UserAccount getUserAccountByUsernamePassword(String uName, String pwd) {
		em.getTransaction().begin();
		Query parameterisedQuery = em.createNamedQuery("UserAccount.findByUserNamePassword");
		parameterisedQuery.setParameter(1, uName);
		parameterisedQuery.setParameter(2, pwd);
		UserAccount useraccount = (UserAccount) parameterisedQuery.getSingleResult();
		em.close();
		return useraccount;

	}

	// fetch all User Accounts
	public ArrayList<UserAccount> fetchAllUsers() {
		ArrayList<UserAccount> users = (ArrayList<UserAccount>) em.createQuery("select u from UserAccount u")
				.getResultList();

		return users;
	}
}
