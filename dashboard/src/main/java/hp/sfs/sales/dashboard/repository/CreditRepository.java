package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.CreditRecord;

public interface CreditRepository extends JpaRepository<CreditRecord, Long> {

}
