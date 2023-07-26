package hp.sfs.sales.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import hp.sfs.sales.dashboard.entity.Debtor;
import hp.sfs.sales.dashboard.service.DebtorService;

@RestController
@RequestMapping("/debtor")
public class DebtorController {

	@Autowired
	DebtorService service;

	@GetMapping
	public ResponseEntity<?> getAllDebtors() {
		List<Debtor> debtorList = service.getAllDebtors();
		if (debtorList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("Debtors are not avaialble");

		return ResponseEntity.status(HttpStatus.OK).body(debtorList);
	}

	@PostMapping
	public ResponseEntity<?> saveOperator(@RequestBody Map<String, String> map) {
		ObjectMapper mapper = new ObjectMapper();
		Debtor debtor = mapper.convertValue(map, Debtor.class);
		debtor = service.saveDebtor(debtor);
		return ResponseEntity.status(HttpStatus.OK).body(debtor);
	}

	@PutMapping
	public ResponseEntity<?> updateOperator(@RequestParam Long id, @RequestBody Map<String, String> map) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateDebtor(id, map));
	}

}
