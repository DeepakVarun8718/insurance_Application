package in.deepak.payloads;

import java.time.LocalDate;

import in.deepak.enums.RelationWithApplicant;
import in.deepak.validation.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class DependentDto {
	
	 private int dependentId;


	    @NotBlank(message = "Dependent Name is Mandatory")
	    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "DependentName can only contain letters and spaces")
	    @Size(min = 2, max = 50, message = "Dependent's name must be between 2 and 50 characters")
	    private String dependentName;

	    @NotNull(message = "RelationWithApplicant is Mandatory")
	    @ValidEnum(enumClass =RelationWithApplicant.class, message = "Invalid Relation with Applicant")
	    private RelationWithApplicant relationWithApplicant;

	    @NotNull(message = "Date of birth is Mandatory")
	    @Past(message = "Date of birth must be in the past")
	    private LocalDate dob;
	    private LocalDate createdDate;

}
