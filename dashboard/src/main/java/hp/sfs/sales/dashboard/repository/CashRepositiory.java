package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.Cash;

public interface CashRepositiory extends JpaRepository<Cash, Long> {

}
