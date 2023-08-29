package hp.sfs.sales.dashboard.controller;

import jakarta.validation.Valid;
import java.util.List;

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

import hp.sfs.sales.dashboard.entity.Debtor;
import hp.sfs.sales.dashboard.service.DebtorService;

@RestController
@RequestMapping("/debtor")
public class DebtorController {

	@Autowired
	DebtorService service;

	@GetMapping("/")
	public ResponseEntity<?> getAllDebtors() {
		List<Debtor> debtorList = service.getAllDebtors();
		if (debtorList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("Debtors are not available");

		return ResponseEntity.status(HttpStatus.OK).body(debtorList);
	}

	@PostMapping("/")
	public ResponseEntity<?> saveOperator(@Valid @RequestBody Debtor debtor) {
		debtor = service.saveDebtor(debtor);
		return ResponseEntity.status(HttpStatus.OK).body(debtor);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateOperator(@RequestParam Long id, @Valid @RequestBody Debtor debtor) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateDebtor(id, debtor));
	}

}
