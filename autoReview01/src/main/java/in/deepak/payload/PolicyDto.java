package in.deepak.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDto {

	
	    private Integer policyId;
	    private String policyName;
	    private String description;
	    private long coverageAmount;
	    private int ruleId;
	    
	    private List<PremiumDto> premiums = new ArrayList<>();
	    
}
