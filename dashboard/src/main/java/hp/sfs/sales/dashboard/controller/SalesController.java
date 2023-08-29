package hp.sfs.sales.dashboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hp.sfs.sales.dashboard.dto.AllSalesDetailsDto;
import hp.sfs.sales.dashboard.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	SalesService service;

	@PostMapping(path ="/save")
	public ResponseEntity<?> saveSalesDetails(@RequestBody AllSalesDetailsDto salesDetails) {
		try {
			service.saveSalesDetails(salesDetails);
			return ResponseEntity.status(HttpStatus.OK).body("Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
		}
	}
}
