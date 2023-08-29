package hp.sfs.sales.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Debtor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("isValid")
	private boolean isValid = true;
}
