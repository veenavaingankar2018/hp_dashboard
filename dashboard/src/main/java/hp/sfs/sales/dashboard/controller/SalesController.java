package hp.sfs.sales.dashboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hp.sfs.sales.dashboard.dto.OnlineDepositDto;
import hp.sfs.sales.dashboard.dto.SaleDetailsDto;
import hp.sfs.sales.dashboard.dto.SalesRecordDto;

@RestController
@RequestMapping("/sales")
public class SalesController {

	@PostMapping
	public ResponseEntity<?> saveSalesDetails(@RequestBody SaleDetailsDto salesDetails) {
		SalesRecordDto saleDetailsDto=salesDetails.getSalesRecord();
		List<OnlineDepositDto> onlineDepositList = salesDetails.getOnlineDeposit();
		return ResponseEntity.status(HttpStatus.OK).body(onlineDepositList);
	}
}
