package hp.sfs.sales.dashboard.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OilSaleDto {
	//private String date;
	private String productName;
	private Integer quantity;
	private Double amount;
}
