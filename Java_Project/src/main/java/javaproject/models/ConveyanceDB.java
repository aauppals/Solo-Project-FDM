package javaproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          POJO (plain old java object) for the Conveyance Database. Records
 *          are matched to it using the primary key of Conveyance Registry (as
 *          foreign key)
 */
@Entity
@Table(name = "Project_ConveyanceDB")
public class ConveyanceDB {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int convID;

	private String convName;
	private double convPrice;

	@ManyToOne
	@JoinColumn(name = "FK_ConvReg_Id")
	private ConveyanceRegistry conveyanceregistry;

	public ConveyanceDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConveyanceDB(String convName, double convPrice, ConveyanceRegistry conveyanceregistry) {
		super();
		this.convName = convName;
		this.convPrice = convPrice;
		this.conveyanceregistry = conveyanceregistry;
	}

	public int getConvID() {
		return convID;
	}

	public String getConvName() {
		return convName;
	}

	public void setConvName(String convName) {
		this.convName = convName;
	}

	public double getConvPrice() {
		return convPrice;
	}

	public void setConvPrice(double convPrice) {
		this.convPrice = convPrice;
	}

	public ConveyanceRegistry getConveyanceregistry() {
		return conveyanceregistry;
	}

	public void setConveyanceregistry(ConveyanceRegistry conveyanceregistry) {
		this.conveyanceregistry = conveyanceregistry;
	}

	@Override
	public String toString() {
		return "ConveyanceDB [convID=" + convID + ", convName=" + convName + ", convPrice=" + convPrice + "]";
	}

}
