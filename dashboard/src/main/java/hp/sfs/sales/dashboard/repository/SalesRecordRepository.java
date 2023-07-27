package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.SalesRecord;

public interface SalesRecordRepository extends JpaRepository<SalesRecord, Long> {

}
