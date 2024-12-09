package in.deepak.validationImpl;

import in.deepak.repository.UserRepository;
import in.deepak.validation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
	
	private UserRepository userRepository;
	
	public UniqueEmailValidator(UserRepository userRepository) {
		
		this.userRepository=userRepository;
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		if(email==null) {
			return false;
		}
		
		boolean emailCheck = userRepository.existsByEmail(email);
		
		return !emailCheck;
	}

}
