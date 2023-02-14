package javaproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaproject.models.ConveyanceRegistry;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          The Conveyance Registry Data Access Object (Dao) is responsible
 *          for writing and reading data from the Conveyance Registry database, using the
 *          entity manager factory and entity manager (JPA).
 */
public class ConveyanceRegistryDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ConveyanceRegistryDao() {
		super();
		this.emf = Persistence.createEntityManagerFactory("Java_Project");
		this.em = emf.createEntityManager();
	}

	public void addConvRegistry(ConveyanceRegistry conveyanceregistry) {
		em.getTransaction().begin();
		em.persist(conveyanceregistry);
		em.getTransaction().commit();
		em.close();
	}

	// find ConveyanceRegistry by ID
	public ConveyanceRegistry findByconvregID(int id) {
		em.getTransaction().begin();
		ConveyanceRegistry convreg = em.find(ConveyanceRegistry.class, id);
		em.close();
		return convreg;

	}

}
