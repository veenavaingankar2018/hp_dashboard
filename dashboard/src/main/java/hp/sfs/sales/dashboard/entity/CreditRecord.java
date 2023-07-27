package hp.sfs.sales.dashboard.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne			// ToDo: Need to check and brainstorm
	private Debtor debtor;
	
	private String transactionType;	//ToDo: make this enum
	
	private String driverName;
	
	private String vehicleNumber;
	
	private String product;		//ToDo: Make this enum
	
	private Double amount;
	
	private Timestamp recieptTime;
		
}
