package hp.sfs.sales.dashboard.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllSalesDetailsDto {
	Long operatorId;
	
	Double cashCollected;
	
	@JsonProperty("salesDetails")
	List<SalesRecordDto> salesRecords;

	@JsonProperty("oilSales")
	List<OilSaleDto> oilSales;
	
	@JsonProperty("onlineDeposit")
	List<OnlineDepositDto> onlineDeposits;
	
	@JsonProperty("creditDetails")
	List<CreditTransactionDto> creditTransactions;

	@JsonProperty("expenses")
	List<ExpenseDto> expenses;
}
