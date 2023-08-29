package hp.sfs.sales.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Operator updateOperator(Long id, Operator newOperator) {
		Operator operator = repo.findById(id).get();
		operator.setName(newOperator.getName());
		operator.setValid(newOperator.isValid());
		return repo.save(operator);
	}
}
