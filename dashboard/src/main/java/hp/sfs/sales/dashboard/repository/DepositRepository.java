package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
