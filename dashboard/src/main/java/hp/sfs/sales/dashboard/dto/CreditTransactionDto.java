package hp.sfs.sales.dashboard.dto;

import hp.sfs.sales.dashboard.enums.CreditTransactionType;
import hp.sfs.sales.dashboard.enums.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditTransactionDto {
	private CreditTransactionType transactionType;
	
	private String driverName;
	
	private String vehicleNumber;
	
	private ProductType product;
	
	private Double amount;
	
	//private Timestamp recieptTime;
}
