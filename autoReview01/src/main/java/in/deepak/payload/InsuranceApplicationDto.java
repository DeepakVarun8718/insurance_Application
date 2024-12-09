package in.deepak.payload;

import java.time.LocalDate;

import in.deepak.enums.PolicyStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceApplicationDto {

	
	private int insuranceId;
	
    private String policyName;
    
    private long coverageAmount;
    
    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;
    
    private double monthlyPremium;
    
    private double healthStatus;
    
    private int policyId;
    
    private int userId;
    
    private LocalDate createdDate;
    
    private LocalDate updatedDate;
}
