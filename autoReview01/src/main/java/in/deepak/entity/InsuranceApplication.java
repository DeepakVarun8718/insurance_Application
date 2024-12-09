package in.deepak.entity;

import java.time.LocalDate;

import in.deepak.enums.PolicyStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="insuranceApplication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceApplication {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insuranceId;
    private String policyName;
    private long coverageAmount;
    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;
    private double monthlyPremium;
    private double healthStatus;
    private int policyId;
    private int userId;
    private String nominee;
    @OneToOne
    @JoinColumn(name = "health_id")
    private HealthDetails healthDetails;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    

}
