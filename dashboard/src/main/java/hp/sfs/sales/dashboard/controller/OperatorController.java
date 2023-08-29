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
import hp.sfs.sales.dashboard.entity.Operator;
import hp.sfs.sales.dashboard.service.OperatorService;

@RestController
@RequestMapping("/operator")
public class OperatorController {
	@Autowired
	OperatorService service;

	@GetMapping("/")
	public ResponseEntity<?> getAllOperators() {
		List<Operator> operatorList = service.getAllOperatos();
		if (operatorList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("Operators are not available");

		return ResponseEntity.status(HttpStatus.OK).body(operatorList);
	}

	@PostMapping("/")
	public ResponseEntity<?> saveOperator(@Valid @RequestBody Operator operator) {
		operator = service.saveOperator(operator);
		return ResponseEntity.status(HttpStatus.OK).body(operator);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateOperator(@RequestParam Long id, @Valid @RequestBody Operator operator) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateOperator(id, operator));
	}
}
