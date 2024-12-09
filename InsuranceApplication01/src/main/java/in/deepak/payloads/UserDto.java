package in.deepak.payloads;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import in.deepak.enums.EmploymentType;
import in.deepak.enums.Gender;
import in.deepak.enums.IncomeSlab;
import in.deepak.enums.Role;
import in.deepak.validation.AdultAge;
import in.deepak.validation.UniqueAadhar;
import in.deepak.validation.UniqueEmail;
import in.deepak.validation.ValidEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
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
@ToString
public class UserDto {
	
    @Email(message ="Invalid email format")
    @UniqueEmail
    @NotBlank(message = "Email is Mandatory")
    private String email;

    @NotBlank(message = "Name is Mandatory")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message ="Name can only contain letters and spaces")
    @Size(min = 2, max = 50, message ="Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Password is Mandatory")
    @Size(min = 6, max = 20, message ="Password must be between 6 and 20 characters")
    private String password;

    

    @NotBlank(message = "Mobile Number is Mandatory")
    @Pattern(regexp = "\\d{10}", message ="Invalid mobile number format")
    private String  mobileNumber;

    @NotBlank(message = "Address is Mandatory")
    @Size(max = 100, message ="Address must not exceed 100 characters")
    private String address;

    @NotBlank(message = "Aadhaar number is Mandatory")
    @Pattern(regexp = "\\d{12}", message = "Invalid Aadhaar number format")
    @UniqueAadhar
    private String aadhaarNumber;

    @NotNull(message = "Date of Birth is Mandatory")
    @Past(message = "Date of birth must be in the past")
    @AdultAge
    private LocalDate dob;

    @NotNull(message = "Gender is Mandatory")
    @ValidEnum(enumClass = Gender.class, message = "Invalid gender value")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @NotNull(message = "Income Slab is Mandatory")
    @ValidEnum(enumClass = IncomeSlab.class,message = "Please Enter Valid Value like LESS_THAN_2 or TWO_TO_FOUR or FOUR_TO_SEVEN or SEVEN_TO_TEN or TEN_PLUS")
    @Enumerated(EnumType.STRING)
    private IncomeSlab incomeSlab;


    @NotNull(message = "Employment Type is Mandatory")
    @ValidEnum(enumClass = EmploymentType.class,message ="Please Enter Valid Value like SALARIED or SELF_EMPLOYED")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    private LocalDate createdDate;
    
    public UserDto() {
    	
    }

    public UserDto(String email, String password) {
    }

	public UserDto(@Email(message = "Invalid email format") @NotBlank(message = "Email is Mandatory") String email,
			@NotBlank(message = "Name is Mandatory") @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Name can only contain letters and spaces") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name,
			@NotBlank(message = "Password is Mandatory") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters") String password,
			@NotBlank(message = "Mobile Number is Mandatory") @Pattern(regexp = "\\d{10}", message = "Invalid mobile number format") String mobileNumber,
			@NotBlank(message = "Address is Mandatory") @Size(max = 100, message = "Address must not exceed 100 characters") String address,
			@NotBlank(message = "Aadhaar number is Mandatory") @Pattern(regexp = "\\d{12}", message = "Invalid Aadhaar number format") String aadhaarNumber,
			@NotNull(message = "Date of Birth is Mandatory") @Past(message = "Date of birth must be in the past") LocalDate dob,
			@NotNull(message = "Gender is Mandatory") Gender gender,
			@NotNull(message = "Income Slab is Mandatory") IncomeSlab incomeSlab,
			@NotNull(message = "Employment Type is Mandatory") EmploymentType employmentType, Role role,
			LocalDate createdDate) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.aadhaarNumber = aadhaarNumber;
		this.dob = dob;
		this.gender = gender;
		this.incomeSlab = incomeSlab;
		this.employmentType = employmentType;
		this.role = role;
		this.createdDate = createdDate;
	}
    
}

