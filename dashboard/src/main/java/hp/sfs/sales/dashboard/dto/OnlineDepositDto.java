package hp.sfs.sales.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineDepositDto {
	private String mode;
	private Double amount;
	private String remark;
}
