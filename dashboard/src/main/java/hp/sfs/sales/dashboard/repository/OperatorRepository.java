package hp.sfs.sales.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hp.sfs.sales.dashboard.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

}
