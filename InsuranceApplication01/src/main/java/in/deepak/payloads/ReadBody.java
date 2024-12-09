package in.deepak.payloads;

import in.deepak.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReadBody {

//	 @NotBlank(message = "Name is Mandatory")
//	    @Pattern(regexp = "^[\\p{L}\\s]+$", message ="Name can only contain letters and spaces")
//	    @Size(min = 2, max = 50, message ="Name must be between 2 and 50 characters")
	private String name;
	
//
//    @Email(message ="Invalid email format")
//    @NotBlank(message = "Email is Mandatory")
	private String email;
	
//    @NotBlank(message = "Mobile Number is Mandatory")
//    @Pattern(regexp = "\\d{10}", message ="Invalid mobile number format")
	private String number;
	
}
