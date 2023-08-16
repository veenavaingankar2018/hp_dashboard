package hp.sfs.sales.dashboard.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
public class Deposit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Operator operator;

	private Timestamp recordDate;

	private Double totalSalesValue;

	private Double netCollection;

	@OneToOne
	private Cash cashCollected;
	
	@OneToMany
	private List<SalesRecord> salesRecords;
	
	@OneToMany
	private List<OilSalesRecord> oilSalesRecords;
	
	@OneToMany
	private List<OnlineDeposit> onlineDeposits;
	
	@OneToMany
	private List<CreditRecord> creditRecords;

	@OneToMany
	private List<Expense> expenses;
}
