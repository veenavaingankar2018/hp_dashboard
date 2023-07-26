package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.Debtor;

public interface DebtorRepository extends JpaRepository<Debtor, Long> {

}
