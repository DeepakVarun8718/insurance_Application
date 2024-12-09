package in.deepak.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import in.deepak.enums.*;


@Entity
@Table(name="healthDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthDetails {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int healthId;
	    private Boolean anySurgery;
	    private Disease diseases;
	    private Boolean alcoholConsumption;
	    private Boolean tobaccoConsumption;
	    private Boolean smokingStatus;
	    private LocalDate createdDate;
}
