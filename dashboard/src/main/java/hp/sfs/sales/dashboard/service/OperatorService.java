package hp.sfs.sales.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hp.sfs.sales.dashboard.entity.Operator;
import hp.sfs.sales.dashboard.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository repo;

	public List<Operator> getAllOperatos() {
		return repo.findAll();
	}

	public Operator saveOperator(Operator operator) {
		return repo.save(operator);
	}

	public Operator updateOperator(Long id, Map<String, String> map) {
		Operator operator = repo.findById(id).get();
		ObjectMapper mapper = new ObjectMapper();
		Operator operatorDto = mapper.convertValue(map, Operator.class);
		operator.setName(operatorDto.getName());
		operator.setValid(operatorDto.isValid());
		return repo.save(operator);
	}
}
