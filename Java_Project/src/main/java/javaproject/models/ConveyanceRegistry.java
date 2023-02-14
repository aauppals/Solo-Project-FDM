package javaproject.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ahmad
 * @version 1.0
 * 
 *          POJO (plain old java object) for the Conveyance Registry. Records
 *          are written to it using the primary key of User Account (as
 *          foreign key)
 */
@Entity
@Table(name = "Project_Conveyance_Registry")
public class ConveyanceRegistry {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int convReg;

	private String convType;

	@ManyToOne
	@JoinColumn(name = "FK_UserAccount_Id")
	private UserAccount useraccount;

	@OneToMany(mappedBy = "conveyanceregistry")
	private List<ConveyanceDB> convDB = new ArrayList<ConveyanceDB>();

	public ConveyanceRegistry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConveyanceRegistry(String convType, UserAccount useraccount) {
		super();
		this.convType = convType;
		this.useraccount = useraccount;
	}

	public int getConvReg() {
		return convReg;
	}

	public String getConvType() {
		return convType;
	}

	public void setConvType(String convType) {
		this.convType = convType;
	}

	public UserAccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}

	public List<ConveyanceDB> getConvDB() {
		return convDB;
	}

	public void setConvDB(List<ConveyanceDB> convDB) {
		this.convDB = convDB;
	}

	@Override
	public String toString() {
		return "ConveyanceRegistry [convReg=" + convReg + ", convType=" + convType + ", useraccount=" + useraccount
				+ ", convDB=" + convDB + "]";
	}

}
