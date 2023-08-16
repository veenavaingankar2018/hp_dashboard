package hp.sfs.sales.dashboard.repository;

import hp.sfs.sales.dashboard.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
