package in.deepak.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyRuleDto {
	

    private int ruleId;
    private int minAge;
    private int maxAge;
    private int healthStatus;
    private double bmi;
    private List<PolicyDto> policies = new ArrayList<>();

}
