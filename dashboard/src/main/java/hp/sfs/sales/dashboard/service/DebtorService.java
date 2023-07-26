package hp.sfs.sales.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hp.sfs.sales.dashboard.entity.Debtor;
import hp.sfs.sales.dashboard.repository.DebtorRepository;

@Service
public class DebtorService {
	@Autowired
	DebtorRepository repository;

	public List<Debtor> getAllDebtors() {
		return repository.findAll();
	}

	public Debtor saveDebtor(Debtor debtor) {
		return repository.save(debtor);
	}

	public Debtor updateDebtor(Long id, Map<String, String> map) {
		Debtor debtor = repository.findById(id).get();
		ObjectMapper mapper = new ObjectMapper();
		Debtor debtorDto = mapper.convertValue(map, Debtor.class);
		debtor.setName(debtorDto.getName());
		debtor.setValid(debtorDto.isValid());
		return repository.save(debtor);
	}

}
