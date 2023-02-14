package javaproject.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import javaproject.models.UserAccount;

class UserAccountDaoTest {

	@Test
	void abletoFindUserAccountId() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();
		
		UserAccount useraccount = new UserAccount();
		Object identity = 12345;
		
		useraccount = em.find(UserAccount.class, identity);
		assertEquals (identity, 12345);
		
		
	}

}
