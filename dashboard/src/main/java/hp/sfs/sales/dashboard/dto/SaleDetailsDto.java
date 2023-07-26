package hp.sfs.sales.dashboard.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDetailsDto {
	@JsonProperty("salesDetails")
	SalesRecordDto salesRecord;
	
	@JsonProperty("onlineDeposit")
	List<OnlineDepositDto> onlineDeposit;
}
