package in.deepak.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PremiumDto {

	 private int policyId;
	    private double monthlyPremium;
	    private String policyName;
	    private String description;
	    private long coverageAmount;
}
