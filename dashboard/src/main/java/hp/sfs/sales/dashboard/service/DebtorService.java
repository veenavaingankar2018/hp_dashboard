package hp.sfs.sales.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Debtor updateDebtor(Long id, Debtor newDebtor) {
		Debtor debtor = repository.findById(id).get();
		debtor.setName(newDebtor.getName());
		debtor.setValid(newDebtor.isValid());
		return repository.save(debtor);
	}

}
