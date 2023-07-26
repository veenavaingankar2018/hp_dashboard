package hp.sfs.sales.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
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
import hp.sfs.sales.dashboard.entity.Operator;
import hp.sfs.sales.dashboard.service.OperatorService;

@RestController
@RequestMapping("/operatos")
public class OperatorController {
	@Autowired
	OperatorService service;

	@GetMapping
	public ResponseEntity<?> getAllOperatos() {
		List<Operator> operatorList = service.getAllOperatos();
		if (operatorList.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("Operators are not available");

		return ResponseEntity.status(HttpStatus.OK).body(operatorList);
	}

	@PostMapping
	public ResponseEntity<?> saveOperator(@RequestBody Map<String, String> map) {
		ObjectMapper mapper = new ObjectMapper();
		Operator operator = mapper.convertValue(map, Operator.class);
		operator = service.saveOperator(operator);
		return ResponseEntity.status(HttpStatus.OK).body(operator);
	}

	@PutMapping
	public ResponseEntity<?> updateOperator(@RequestParam Long id, @RequestBody Map<String, String> map) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateOperator(id, map));
	}
}
