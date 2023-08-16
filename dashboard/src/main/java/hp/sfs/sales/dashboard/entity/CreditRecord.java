package hp.sfs.sales.dashboard.entity;

import hp.sfs.sales.dashboard.enums.CreditTransactionType;
import hp.sfs.sales.dashboard.enums.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

	@Enumerated(EnumType.STRING)
	private CreditTransactionType transactionType;
	
	private String driverName;
	
	private String vehicleNumber;

	@Enumerated(EnumType.STRING)
	private ProductType product;
	
	private Double amount;
	
	private Timestamp recieptTime;
		
}
