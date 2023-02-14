package javaproject.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          POJO (plain old java object) for the Location Registry. Records are
 *          written to it using the primary key of the UserAccount DB (as
 *          foreign key)
 */
@Entity
@Table(name = "Project_Location_Registry")
public class LocationRegistry {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int locationReg;

	private String locationType;
	private int stayDuration;

	@ManyToOne
	@JoinColumn(name = "FK_UserAccount_Id")
	private UserAccount useraccount;

	@OneToMany(mappedBy = "locationregistry")
	private List<LocationDB> locDB = new ArrayList<LocationDB>();

	public LocationRegistry() {
		super();

	}

	public LocationRegistry(String locationType, int stayDuration, UserAccount useraccount) {
		super();
		this.locationType = locationType;
		this.stayDuration = stayDuration;
		this.useraccount = useraccount;
	}

	public int getLocationReg() {
		return locationReg;
	}

	public List<LocationDB> getLocDB() {
		return locDB;
	}

	public void setLocDB(List<LocationDB> locDB) {
		this.locDB = locDB;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public int getStayDuration() {
		return stayDuration;
	}

	public void setStayDuration(int stayDuration) {
		this.stayDuration = stayDuration;
	}

	public UserAccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}

	@Override
	public String toString() {
		return "LocationRegistry [locationReg=" + locationReg + ", locationType=" + locationType + ", stayDuration="
				+ stayDuration + ", useraccount=" + useraccount + ", locDB=" + locDB + "]";
	}

}
