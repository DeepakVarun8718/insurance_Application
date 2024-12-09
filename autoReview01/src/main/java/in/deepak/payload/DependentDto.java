package in.deepak.payload;

import java.time.LocalDate;

import in.deepak.enums.RelationWithApplicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependentDto {
	
	
	    private int dependentId;
	    private String dependentName;
	    private RelationWithApplicant relationWithApplicant;
	    private LocalDate dob;
	    

}
