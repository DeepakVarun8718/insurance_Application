package in.deepak.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	
	    private int userId;
	    private String email;

	    private String name;
	    private String password;

	    private long mobileNumber;
	    private String address;
	    private long aadhaarNumber;
	    private LocalDate dob;
	    private String role;

}
