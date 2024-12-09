package in.deepak.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyRuleDto {

	private int ruleId;

    @NotNull(message = "Age is Mandatory")
    private Integer minAge;

    @NotNull(message = "Age is Mandatory")
    private Integer maxAge;

    @NotNull(message = "healthStatus is Mandatory")
    private Integer healthStatus;
    private double bmi;
    private List<PolicyDto> policies = new ArrayList<>();
    private LocalDate createdDate;
    
}
