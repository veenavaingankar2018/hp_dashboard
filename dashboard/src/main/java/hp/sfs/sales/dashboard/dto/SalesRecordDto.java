package hp.sfs.sales.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesRecordDto {
	private String startTime;
	private String endTime;
	private String product;
	private Double rate;
	private Double startReading;
	private Double endReading;
	private Double salesVolume;
	private Double pumpTestVolume;
	private Double amount;
}
