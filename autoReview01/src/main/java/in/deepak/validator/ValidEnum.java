package in.deepak.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.deepak.validatorImpl.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface ValidEnum {

	  String message() default "Invalid enum value";
	    
	    Class<?>[] groups() default {};
	   
	    Class<? extends Payload>[] payload() default {};
	    
	    Class<? extends Enum<?>> enumClass();
	
}
