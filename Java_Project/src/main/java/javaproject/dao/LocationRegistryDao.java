package javaproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaproject.models.LocationRegistry;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          The Location Registry Data Access Object (Dao) is responsible
 *          for writing and reading data from the Location Registry database, using the
 *          entity manager factory and entity manager (JPA).
 */
public class LocationRegistryDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public LocationRegistryDao() {
		super();
		this.emf = Persistence.createEntityManagerFactory("Java_Project");
		this.em = emf.createEntityManager();
	}

	public void addLocRegistry(LocationRegistry locationregistry) {
		em.getTransaction().begin();
		em.persist(locationregistry);
		em.getTransaction().commit();
		em.close();
	}

	// find LocationRegistry by Id
	public LocationRegistry findBylocregID(int id) {
		em.getTransaction().begin();
		LocationRegistry locreg = em.find(LocationRegistry.class, id);
		em.close();
		return locreg;
	}

	// Add fetch all LocRegistries
}
