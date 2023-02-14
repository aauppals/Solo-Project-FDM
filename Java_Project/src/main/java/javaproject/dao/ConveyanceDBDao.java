package javaproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaproject.models.ConveyanceDB;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          The Conveyance Database Data Access Object (DB-Dao) is responsible
 *          for writing and reading data from the Conveyance DB, using the
 *          entity manager factory and entity manager (JPA).
 */
public class ConveyanceDBDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ConveyanceDBDao() {
		super();
		this.emf = Persistence.createEntityManagerFactory("Java_Project");
		this.em = emf.createEntityManager();
	}

	public void addConvDB(ConveyanceDB conveyancedb) {
		em.getTransaction().begin();
		em.persist(conveyancedb);
		em.getTransaction().commit();
		em.close();
	}

	// find ConveyanceDB by Id
	public ConveyanceDB findbyconvDBID(int id) {
		em.getTransaction().begin();
		ConveyanceDB convdb = em.find(ConveyanceDB.class, id);
		em.close();
		return convdb;
	}

}
