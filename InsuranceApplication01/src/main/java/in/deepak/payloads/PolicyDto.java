package in.deepak.payloads;

import java.time.LocalDate;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PolicyDto {
	
	 private Integer policyId;

	    @NotBlank(message = "Policy Name is Mandatory")
	    @Pattern(regexp = "^[\\p{L}\\s]+$", message ="Policy Name can only contain letters and spaces")
	    @Size(min = 2, max = 50, message ="Policy Name must be between 2 and 50 characters")
	    private String policyName;

	    @NotBlank(message = "Policy Description is Mandatory")
	    @Size(min = 10,message = "Policy Description must be of min 10 character")
	    private String description;


	    @NotNull(message = "Coverage Amount is Mandatory")
	    private long coverageAmount;
	    
	    private int ruleId;
	    
	    private LocalDate createdDate;
	    

}
