package javaproject.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          POJO (plain old java object) for the Location Database. Records
 *          are written to it using the primary key of Location Registry (as
 *          foreign key)
 */
@Entity
@Table(name = "Project_LocationDB")
public class LocationDB {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int locationID;

	private String propertyName;
	private double propertyPrice;

	@ManyToOne
	@JoinColumn(name = "FK_LocationReg_Id")
	private LocationRegistry locationregistry;

	public LocationDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationDB(String propertyName, double propertyPrice, LocationRegistry locationregistry) {
		super();
		this.propertyName = propertyName;
		this.propertyPrice = propertyPrice;
		this.locationregistry = locationregistry;
	}

	public int getLocationID() {
		return locationID;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public LocationRegistry getLocationregistry() {
		return locationregistry;
	}

	public void setLocationregistry(LocationRegistry locationregistry) {
		this.locationregistry = locationregistry;
	}

	@Override
	public String toString() {
		return "LocationDB [locationID=" + locationID + ", propertyName=" + propertyName + ", propertyPrice="
				+ propertyPrice + "]"; // removed location registry - was causing StackOverFlow!!
	}

}
