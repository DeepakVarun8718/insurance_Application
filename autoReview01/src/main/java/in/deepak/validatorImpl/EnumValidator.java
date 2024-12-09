package in.deepak.validatorImpl;

import in.deepak.validator.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>>{

	
	 private Class<? extends Enum<?>> enumClass;
	 
	 
	    @Override
	    public void initialize(ValidEnum constraintAnnotation) {
	        this.enumClass = constraintAnnotation.enumClass();
	    }
	   
	    
	    @Override
	    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
	        for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
	            if (enumConstant.name().equals(value.name())) {
	                return true;
	            }
	        }
	        return false;
	    }
}
