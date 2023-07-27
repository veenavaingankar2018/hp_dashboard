package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.OilSalesRecord;

public interface OilSalesRepository extends JpaRepository<OilSalesRecord, Long> {

}
