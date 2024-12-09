package in.deepak.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.deepak.validationImpl.UniqueAadharValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueAadharValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAadhar {

	String message() default "Aadhaar already exists";
	
 Class<?>[] groups() default {};
	 
	 Class<? extends Payload>[] payload() default {};
}
