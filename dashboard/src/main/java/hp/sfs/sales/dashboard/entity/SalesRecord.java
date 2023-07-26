package hp.sfs.sales.dashboard.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SalesRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Operator operator;
//	private Timestamp startTime;
//	private Timestamp endTime;
	private String product;
	private Double rate;
	private Integer startReading;
	private Integer endReading;
	private Integer salesVolume;
	private Integer pumpTestVolume;
	private Double amount;
	@OneToOne
	private Deposit deposit;
}
