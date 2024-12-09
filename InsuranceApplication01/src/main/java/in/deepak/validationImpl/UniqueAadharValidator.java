package in.deepak.validationImpl;

import in.deepak.repository.UserRepository;
import in.deepak.validation.UniqueAadhar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueAadharValidator implements ConstraintValidator<UniqueAadhar, String>{

	private UserRepository userRepository;
	
	public UniqueAadharValidator(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public boolean isValid(String aadhar, ConstraintValidatorContext context) {
		
		if(aadhar==null) {
			return false;
		}
		
		return !userRepository.existsByAadhaarNumber(aadhar);
	}
	
	
	

}
