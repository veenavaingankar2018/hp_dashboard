package hp.sfs.sales.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesRecordDto {
	private Long operatorId;
	private String product;
	private Double rate;
	private Integer startReading;
	private Integer endReading;
	private Integer salesVolume;
	private Integer pumpTestVolume;
	private Double amount;
}
