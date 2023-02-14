package javaproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaproject.models.LocationDB;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          The Location Database Data Access Object (DB-Dao) is responsible
 *          for writing and reading data from the Location DB, using the
 *          entity manager factory and entity manager (JPA).
 */
public class LocationDBDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public LocationDBDao() {
		super();
		this.emf = Persistence.createEntityManagerFactory("Java_Project");
		this.em = emf.createEntityManager();
	}

	public void addLocDB(LocationDB locationdb) {
		em.getTransaction().begin();
		em.persist(locationdb);
		em.getTransaction().commit();
		em.close();
	}

	// find LocationDB by Id
	public LocationDB findbylocDBID(int id) {
		em.getTransaction().begin();
		LocationDB locdb = em.find(LocationDB.class, id);
		em.close();
		return locdb;
	}

	/*
	 * public void addLocRegtoLocDB(int locregID, int locdbID) { LocationRegistryDao
	 * locregdao = new LocationRegistryDao(); LocationRegistry locreg =
	 * locregdao.findBylocregID(locregID); LocationDB locDB =
	 * findbylocDBID(locdbID); addLocRegtoLocDB(locreg,locDB); }
	 * 
	 * private void addLocRegtoLocDB(LocationRegistry locreg, LocationDB locDB) {
	 * em.getTransaction().begin(); locDB.getLocationregistry().add(locreg);
	 * locreg.getLocationdb().add(locDB); em.getTransaction().commit(); em.close();
	 * 
	 * }
	 */

}
