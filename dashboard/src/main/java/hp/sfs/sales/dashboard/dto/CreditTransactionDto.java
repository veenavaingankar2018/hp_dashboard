package hp.sfs.sales.dashboard.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditTransactionDto {
	private String transactionType;	//ToDo: make this enum
	
	private String driverName;
	
	private String vehicleNumber;
	
	private String product;		//ToDo: Make this enum
	
	private Double amount;
	
	//private Timestamp recieptTime;
}
