package in.deepak.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.deepak.validationImpl.AdultAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AdultAgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultAge {
	
	String message() default "DOB should be 18 or above";

	 Class<?>[] groups() default {};
	 
	 Class<? extends Payload>[] payload() default {};
}
